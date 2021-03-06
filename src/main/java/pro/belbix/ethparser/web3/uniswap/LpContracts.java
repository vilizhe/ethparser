package pro.belbix.ethparser.web3.uniswap;

import static pro.belbix.ethparser.web3.ContractConstants.D18;
import static pro.belbix.ethparser.web3.ContractConstants.D6;
import static pro.belbix.ethparser.web3.ContractConstants.D8;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.DAI;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.IDX_ETH_DPI;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.SUSHI_ETH_DAI;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.SUSHI_ETH_USDC;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.SUSHI_ETH_USDT;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.SUSHI_ETH_WBTC;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.SUSHI_WBTC_TBTC;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.UNI_ETH_DAI;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.UNI_ETH_DAI_V0;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.UNI_ETH_USDC;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.UNI_ETH_USDC_V0;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.UNI_ETH_USDT;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.UNI_ETH_USDT_V0;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.UNI_ETH_WBTC;
import static pro.belbix.ethparser.web3.harvest.contracts.Vaults.UNI_ETH_WBTC_V0;
import static pro.belbix.ethparser.web3.uniswap.Tokens.BADGER_NAME;
import static pro.belbix.ethparser.web3.uniswap.Tokens.BADGER_TOKEN;
import static pro.belbix.ethparser.web3.uniswap.Tokens.DAI_NAME;
import static pro.belbix.ethparser.web3.uniswap.Tokens.DPI_NAME;
import static pro.belbix.ethparser.web3.uniswap.Tokens.FARM_NAME;
import static pro.belbix.ethparser.web3.uniswap.Tokens.FARM_TOKEN;
import static pro.belbix.ethparser.web3.uniswap.Tokens.IDX_NAME;
import static pro.belbix.ethparser.web3.uniswap.Tokens.TBTC_NAME;
import static pro.belbix.ethparser.web3.uniswap.Tokens.USDC_NAME;
import static pro.belbix.ethparser.web3.uniswap.Tokens.USDT_NAME;
import static pro.belbix.ethparser.web3.uniswap.Tokens.WBTC_NAME;
import static pro.belbix.ethparser.web3.uniswap.Tokens.WETH_NAME;
import static pro.belbix.ethparser.web3.uniswap.Tokens.WETH_TOKEN;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;
import org.web3j.tuples.generated.Tuple2;

public class LpContracts {

    public static final String UNI_LP_ETH_DAI = "0xA478c2975Ab1Ea89e8196811F51A7B7Ade33eB11".toLowerCase();
    public static final String UNI_LP_ETH_USDC = "0xB4e16d0168e52d35CaCD2c6185b44281Ec28C9Dc".toLowerCase();
    public static final String UNI_LP_ETH_USDT = "0x0d4a11d5EEaaC28EC3F61d100daF4d40471f1852".toLowerCase();
    public static final String UNI_LP_ETH_WBTC = "0xBb2b8038a1640196FbE3e38816F3e67Cba72D940".toLowerCase();
    public static final String SUSHI_LP_WBTC_TBTC = "0x2Dbc7dD86C6cd87b525BD54Ea73EBeeBbc307F68".toLowerCase();
    public static final String UNI_LP_USDC_ETH = "0xB4e16d0168e52d35CaCD2c6185b44281Ec28C9Dc".toLowerCase();
    public static final String UNI_LP_USDC_WBTC = "0x004375dff511095cc5a197a54140a24efef3a416".toLowerCase();
    public static final String UNI_LP_USDC_FARM = "0x514906fc121c7878424a5c928cad1852cc545892".toLowerCase();
    public static final String UNI_LP_WETH_FARM = "0x56feaccb7f750b997b36a68625c7c596f0b41a58".toLowerCase();
    public static final String UNI_LP_IDX_ETH = "0x3452a7f30a712e415a0674c0341d44ee9d9786f9".toLowerCase();
    public static final String UNI_LP_USDC_IDX = "0xc372089019614e5791b08b5036f298d002a8cbef".toLowerCase();
    public static final String UNI_LP_ETH_DPI = "0x4d5ef58aac27d99935e5b6b4a6778ff292059991".toLowerCase();
    public static final String UNI_LP_WBTC_BADGER = "0xcd7989894bc033581532d2cd88da5db0a4b12859".toLowerCase();
    public static final String SUSHI_LP_ETH_DAI = "0xc3d03e4f041fd4cd388c549ee2a29a9e5075882f".toLowerCase();
    public static final String SUSHI_LP_ETH_USDC = "0x397ff1542f962076d0bfe58ea045ffa2d347aca0".toLowerCase();
    public static final String SUSHI_LP_ETH_USDT = "0x06da0fd433c1a5d7a4faa01111c044910a184553".toLowerCase();
    public static final String SUSHI_LP_ETH_WBTC = "0xceff51756c56ceffca006cd410b03ffc46dd3a58".toLowerCase();

    public static final Map<String, String> harvestStrategyToLp = new LinkedHashMap<>();
    public static final Map<String, String> lpNameToHash = new LinkedHashMap<>();
    public static final Map<String, Double> lpHashToDividers = new LinkedHashMap<>();
    public static final Map<String, Tuple2<String, String>> lpHashToCoinNames = new LinkedHashMap<>();
    public final static Map<String, Tuple2<Double, Double>> lpPairsDividers = new LinkedHashMap<>();
    public static final Map<String, String> keyCoinForLp = new LinkedHashMap<>();

    static {
        harvestStrategyToLp.put(UNI_ETH_DAI, UNI_LP_ETH_DAI);
        harvestStrategyToLp.put(UNI_ETH_USDC, UNI_LP_ETH_USDC);
        harvestStrategyToLp.put(UNI_ETH_USDT, UNI_LP_ETH_USDT);
        harvestStrategyToLp.put(UNI_ETH_WBTC, UNI_LP_ETH_WBTC);
        harvestStrategyToLp.put(SUSHI_WBTC_TBTC, SUSHI_LP_WBTC_TBTC);
        harvestStrategyToLp.put(UNI_ETH_DAI_V0, UNI_LP_ETH_DAI);
        harvestStrategyToLp.put(UNI_ETH_USDC_V0, UNI_LP_ETH_USDC);
        harvestStrategyToLp.put(UNI_ETH_USDT_V0, UNI_LP_ETH_USDT);
        harvestStrategyToLp.put(UNI_ETH_WBTC_V0, UNI_LP_ETH_WBTC);
        harvestStrategyToLp.put(SUSHI_ETH_DAI, SUSHI_LP_ETH_DAI);
        harvestStrategyToLp.put(SUSHI_ETH_USDC, SUSHI_LP_ETH_USDC);
        harvestStrategyToLp.put(SUSHI_ETH_USDT, SUSHI_LP_ETH_USDT);
        harvestStrategyToLp.put(SUSHI_ETH_WBTC, SUSHI_LP_ETH_WBTC);
        harvestStrategyToLp.put(IDX_ETH_DPI, UNI_LP_ETH_DPI);

        lpHashToDividers.put(UNI_LP_ETH_DAI, D18);
        lpHashToDividers.put(UNI_LP_ETH_USDC, D18);
        lpHashToDividers.put(UNI_LP_ETH_USDT, D18);
        lpHashToDividers.put(UNI_LP_ETH_WBTC, D18);
        lpHashToDividers.put(SUSHI_LP_WBTC_TBTC, D18);
        lpHashToDividers.put(SUSHI_LP_ETH_DAI, D18);
        lpHashToDividers.put(SUSHI_LP_ETH_USDC, D18);
        lpHashToDividers.put(SUSHI_LP_ETH_USDT, D18);
        lpHashToDividers.put(SUSHI_LP_ETH_WBTC, D18);
        lpHashToDividers.put(UNI_LP_IDX_ETH, D18);
        lpHashToDividers.put(UNI_LP_USDC_IDX, D18);
        lpHashToDividers.put(UNI_LP_ETH_DPI, D18);
        lpHashToDividers.put(UNI_LP_WBTC_BADGER, D18);

        lpHashToCoinNames.put(UNI_LP_ETH_DAI, new Tuple2<>(DAI_NAME, WETH_NAME));
        lpHashToCoinNames.put(UNI_LP_ETH_USDC, new Tuple2<>(USDC_NAME, WETH_NAME));
        lpHashToCoinNames.put(UNI_LP_ETH_USDT, new Tuple2<>(WETH_NAME, USDT_NAME));
        lpHashToCoinNames.put(UNI_LP_ETH_WBTC, new Tuple2<>(WBTC_NAME, WETH_NAME));
        lpHashToCoinNames.put(SUSHI_LP_WBTC_TBTC, new Tuple2<>(WBTC_NAME, TBTC_NAME));
        lpHashToCoinNames.put(SUSHI_LP_ETH_DAI, new Tuple2<>(DAI_NAME, WETH_NAME));
        lpHashToCoinNames.put(SUSHI_LP_ETH_USDC, new Tuple2<>(USDC_NAME, WETH_NAME));
        lpHashToCoinNames.put(SUSHI_LP_ETH_USDT, new Tuple2<>(WETH_NAME, USDT_NAME));
        lpHashToCoinNames.put(SUSHI_LP_ETH_WBTC, new Tuple2<>(WBTC_NAME, WETH_NAME));
        lpHashToCoinNames.put(UNI_LP_IDX_ETH, new Tuple2<>(IDX_NAME, WETH_NAME));
        lpHashToCoinNames.put(UNI_LP_USDC_IDX, new Tuple2<>(IDX_NAME, USDC_NAME));
        lpHashToCoinNames.put(UNI_LP_ETH_DPI, new Tuple2<>(DPI_NAME, WETH_NAME));
        lpHashToCoinNames.put(UNI_LP_WBTC_BADGER, new Tuple2<>(WBTC_NAME, BADGER_NAME));
        lpHashToCoinNames.put(UNI_LP_WETH_FARM, new Tuple2<>(FARM_NAME, WETH_NAME));
        lpHashToCoinNames.put(UNI_LP_USDC_FARM, new Tuple2<>(FARM_NAME, USDC_NAME));

        lpNameToHash.put("UNI_LP_ETH_DAI", UNI_LP_ETH_DAI);
        lpNameToHash.put("UNI_LP_ETH_USDC", UNI_LP_ETH_USDC);
        lpNameToHash.put("UNI_LP_ETH_USDT", UNI_LP_ETH_USDT);
        lpNameToHash.put("UNI_LP_ETH_WBTC", UNI_LP_ETH_WBTC);
        lpNameToHash.put("SUSHI_LP_WBTC_TBTC", SUSHI_LP_WBTC_TBTC);
        lpNameToHash.put("UNI_LP_USDC_ETH", UNI_LP_USDC_ETH);
        lpNameToHash.put("UNI_LP_USDC_WBTC", UNI_LP_USDC_WBTC);
        lpNameToHash.put("UNI_ETH_DAI_V0", UNI_ETH_DAI_V0);
        lpNameToHash.put("UNI_ETH_USDC_V0", UNI_ETH_USDC_V0);
        lpNameToHash.put("UNI_ETH_USDT_V0", UNI_ETH_USDT_V0);
        lpNameToHash.put("UNI_ETH_WBTC_V0", UNI_ETH_WBTC_V0);
        lpNameToHash.put("UNI_LP_USDC_FARM", UNI_LP_USDC_FARM);
        lpNameToHash.put("UNI_LP_IDX_ETH", UNI_LP_IDX_ETH);
        lpNameToHash.put("UNI_LP_USDC_IDX", UNI_LP_USDC_IDX);
        lpNameToHash.put("UNI_LP_ETH_DPI", UNI_LP_ETH_DPI);
        lpNameToHash.put("UNI_LP_WBTC_BADGER", UNI_LP_WBTC_BADGER);

        lpPairsDividers.put(UNI_LP_ETH_DAI, new Tuple2<>(D18, D18));
        lpPairsDividers.put(UNI_LP_ETH_USDC, new Tuple2<>(D6, D18));
        lpPairsDividers.put(UNI_LP_ETH_USDT, new Tuple2<>(D18, D6));
        lpPairsDividers.put(UNI_LP_ETH_WBTC, new Tuple2<>(D8, D18));
        lpPairsDividers.put(SUSHI_LP_WBTC_TBTC, new Tuple2<>(D8, D18));
        lpPairsDividers.put(UNI_LP_USDC_ETH, new Tuple2<>(D6, D18));
        lpPairsDividers.put(UNI_LP_USDC_WBTC, new Tuple2<>(D8, D6));
        lpPairsDividers.put(UNI_LP_USDC_FARM, new Tuple2<>(D18, D6));
        lpPairsDividers.put(SUSHI_LP_ETH_DAI, new Tuple2<>(D18, D18));
        lpPairsDividers.put(SUSHI_LP_ETH_USDC, new Tuple2<>(D6, D18));
        lpPairsDividers.put(SUSHI_LP_ETH_USDT, new Tuple2<>(D18, D6));
        lpPairsDividers.put(SUSHI_LP_ETH_WBTC, new Tuple2<>(D8, D18));
        lpPairsDividers.put(UNI_LP_IDX_ETH, new Tuple2<>(D18, D18));
        lpPairsDividers.put(UNI_LP_USDC_IDX, new Tuple2<>(D18, D6));
        lpPairsDividers.put(UNI_LP_ETH_DPI, new Tuple2<>(D18, D18));
        lpPairsDividers.put(UNI_LP_WBTC_BADGER, new Tuple2<>(D8, D18));
        lpPairsDividers.put(UNI_LP_WETH_FARM, new Tuple2<>(D18, D18));

        keyCoinForLp.put(UNI_LP_USDC_FARM, FARM_TOKEN);
        keyCoinForLp.put(UNI_LP_WETH_FARM, FARM_TOKEN);
        keyCoinForLp.put(UNI_LP_WBTC_BADGER, BADGER_TOKEN);
        keyCoinForLp.put(UNI_LP_USDC_ETH, WETH_TOKEN);
    }

    public static double amountToDouble(BigInteger amount, String lpAddress, String coinAddress) {
        Tuple2<String, String> names = lpHashToCoinNames.get(lpAddress);
        if (names == null) {
            throw new IllegalStateException("Not found names for " + lpAddress);
        }
        String coinName = Tokens.findNameForContract(coinAddress);
        if (coinName == null) {
            throw new IllegalStateException("Not found name for " + coinAddress);
        }
        Tuple2<Double, Double> dividers = lpPairsDividers.get(lpAddress);
        if (dividers == null) {
            throw new IllegalStateException("Not found dividers for " + lpAddress);
        }
        Double divider;
        if (names.component1().equals(coinName)) {
            divider = dividers.component1();
        } else if (names.component2().equals(coinName)) {
            divider = dividers.component2();
        } else {
            throw new IllegalStateException(String.format("Coin %s not compare with LP %s", coinName, lpAddress));
        }

        return amount.doubleValue() / divider;
    }

}
