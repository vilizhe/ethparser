package pro.belbix.ethparser;

import static pro.belbix.ethparser.model.UniswapTx.ADD_LIQ;
import static pro.belbix.ethparser.model.UniswapTx.REMOVE_LIQ;
import static pro.belbix.ethparser.ws.WsService.HARDWORK_TOPIC_NAME;
import static pro.belbix.ethparser.ws.WsService.HARVEST_TRANSACTIONS_TOPIC_NAME;
import static pro.belbix.ethparser.ws.WsService.REWARDS_TOPIC_NAME;
import static pro.belbix.ethparser.ws.WsService.UNI_TRANSACTIONS_TOPIC_NAME;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pro.belbix.ethparser.dto.DtoI;
import pro.belbix.ethparser.dto.HardWorkDTO;
import pro.belbix.ethparser.dto.HarvestDTO;
import pro.belbix.ethparser.dto.UniswapDTO;
import pro.belbix.ethparser.properties.AppProperties;
import pro.belbix.ethparser.web3.Web3Parser;
import pro.belbix.ethparser.web3.Web3Service;
import pro.belbix.ethparser.web3.harvest.contracts.Vaults;
import pro.belbix.ethparser.web3.harvest.decoder.HarvestVaultLogDecoder;
import pro.belbix.ethparser.web3.harvest.parser.HardWorkParser;
import pro.belbix.ethparser.web3.harvest.parser.HarvestTransactionsParser;
import pro.belbix.ethparser.web3.harvest.parser.HarvestVaultParserV2;
import pro.belbix.ethparser.web3.harvest.parser.RewardParser;
import pro.belbix.ethparser.web3.uniswap.UniswapLpLogParser;
import pro.belbix.ethparser.web3.uniswap.UniswapTransactionsParser;
import pro.belbix.ethparser.ws.WsService;

@SpringBootApplication
public class Application {

    private final static Logger log = LoggerFactory.getLogger(Application.class);
    private static boolean web3TransactionsStarted = false;
    private static boolean web3LogsStarted = false;
    public static AtomicBoolean run = new AtomicBoolean(true); //for gentle stop

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        Web3Service web3Service = context.getBean(Web3Service.class);
        UniswapTransactionsParser uniswapTransactionsParser = context.getBean(UniswapTransactionsParser.class);
        HarvestTransactionsParser harvestTransactionsParser = context.getBean(HarvestTransactionsParser.class);
        UniswapLpLogParser uniswapLpLogParser = context.getBean(UniswapLpLogParser.class);
        HarvestVaultParserV2 harvestVaultParser = context.getBean(HarvestVaultParserV2.class);
        RewardParser rewardParser = context.getBean(RewardParser.class);
        HardWorkParser hardWorkParser = context.getBean(HardWorkParser.class);
        WsService ws = context.getBean(WsService.class);
        AppProperties conf = context.getBean(AppProperties.class);

        if (conf.isTestWs()) {
            startFakeDataForWebSocket(ws, conf.getTestWsRate());
        } else {
            if (conf.isParseTransactions()) {
                startParse(web3Service, uniswapTransactionsParser, ws, UNI_TRANSACTIONS_TOPIC_NAME, false);
            }

            if (conf.isParseHarvest()) {
                startParse(web3Service, harvestTransactionsParser, ws, HARVEST_TRANSACTIONS_TOPIC_NAME, false);
            }

            if (conf.isParseUniswapLog()) {
                startParse(web3Service, uniswapLpLogParser, ws, UNI_TRANSACTIONS_TOPIC_NAME, true);
            }

            if (conf.isParseHarvestLog()) {
                startParse(web3Service, harvestVaultParser, ws, HARVEST_TRANSACTIONS_TOPIC_NAME, true);
            }

            if (conf.isParseHardWorkLog()) {
                startParse(web3Service, hardWorkParser, ws, HARDWORK_TOPIC_NAME, true);
            }

            if (conf.isParseRewardsLog()) {
                startParse(web3Service, rewardParser, ws, REWARDS_TOPIC_NAME, true);
            }
        }
    }

    private static void startWeb3SubscribeLog(Web3Service web3Service) {
        if (!web3LogsStarted) {
            web3Service.subscribeLogFlowable();
            web3LogsStarted = true;
        }
    }

    private static void startWeb3SubscribeTx(Web3Service web3Service) {
        if (!web3TransactionsStarted) {
            web3Service.subscribeTransactionFlowable();
            web3TransactionsStarted = true;
        }
    }

    public static void startParse(Web3Service web3Service, Web3Parser parser, WsService ws,
                                  String topicName, boolean logs) {
        if (logs) {
            startWeb3SubscribeLog(web3Service);
        } else {
            startWeb3SubscribeTx(web3Service);
        }
        parser.startParse();

        new Thread(() -> {
            while (run.get()) {
                DtoI dto = null;
                try {
                    dto = parser.getOutput().poll(1, TimeUnit.SECONDS);
                } catch (InterruptedException ignored) {
                }
                if (dto != null && topicName != null) {
                    log.debug("Sent to ws {} {}", topicName, dto);
                    ws.send(topicName, dto);
                }
            }
        }).start();

    }

    private static void startFakeDataForWebSocket(WsService ws, int rate) {
        int count = 0;
        List<String> vaults = new ArrayList<>(Vaults.vaultNames.values());
        HarvestVaultLogDecoder harvestVaultLogDecoder = new HarvestVaultLogDecoder();
        List<String> harvestMethods = new ArrayList<>(harvestVaultLogDecoder.getMethodNamesByMethodId().values());
        while (true) {
            double currentCount = count * new Random().nextDouble();

            UniswapDTO uniswapDTO = new UniswapDTO();
            uniswapDTO.setId("0x" + (count * 1000000));
            uniswapDTO.setAmount(currentCount);
            uniswapDTO.setOtherAmount(currentCount);
            uniswapDTO.setCoin("FARM");
            uniswapDTO.setOtherCoin("USDC");
            uniswapDTO.setHash("0x" + count);
            uniswapDTO.setType(new Random().nextBoolean() ?
                new Random().nextBoolean() ? "BUY" : "SELL" :
                new Random().nextBoolean() ? ADD_LIQ : REMOVE_LIQ);
            uniswapDTO.setPrice(currentCount);
            uniswapDTO.setConfirmed(new Random().nextBoolean());
            uniswapDTO.setLastGas(currentCount / 6);
            uniswapDTO.setBlockDate(Instant.now().plus(count, ChronoUnit.MINUTES).getEpochSecond());
            ws.send(UNI_TRANSACTIONS_TOPIC_NAME, uniswapDTO);

            HarvestDTO harvestDTO = new HarvestDTO();
            harvestDTO.setAmount(currentCount * 10000);
            harvestDTO.setUsdAmount((long) currentCount * 100);
            harvestDTO.setVault(vaults.get(new Random().nextInt(vaults.size() - 1)));
            harvestDTO.setId("0x" + (count * 1000000));
            harvestDTO.setHash("0x" + count);
            harvestDTO.setMethodName(harvestMethods.get(new Random().nextInt(harvestMethods.size() - 1)));
            harvestDTO.setLastUsdTvl(currentCount * 1000000);
            harvestDTO.setConfirmed(new Random().nextBoolean());
            harvestDTO.setLastGas(currentCount / 6);
            harvestDTO.setBlockDate(Instant.now().plus(count, ChronoUnit.MINUTES).getEpochSecond());
            harvestDTO.setLastAllUsdTvl(count * 5.1);
            ws.send(HARVEST_TRANSACTIONS_TOPIC_NAME, harvestDTO);

            HardWorkDTO hardWorkDTO = new HardWorkDTO();
            hardWorkDTO.setId("0x" + (count * 1000000));
            hardWorkDTO.setVault(vaults.get(new Random().nextInt(vaults.size() - 1)));
            hardWorkDTO.setBlockDate(Instant.now().plus(count, ChronoUnit.MINUTES).getEpochSecond());
            hardWorkDTO.setShareChange(count / 1000.0);
            hardWorkDTO.setShareChangeUsd(count / 69.0);
            hardWorkDTO.setShareUsdTotal(count);
            hardWorkDTO.setTvl(count * 60);
            hardWorkDTO.setPerc((double) count / 633.0);
            hardWorkDTO.setPsApr((double) count / 63.0);
            ws.send(HARDWORK_TOPIC_NAME, hardWorkDTO);

            log.info("Msg sent " + currentCount);
            count++;
            try {
                Thread.sleep(rate);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
