package pro.belbix.ethparser.web3.harvest.contracts;

import static pro.belbix.ethparser.web3.ContractConstants.D18;
import static pro.belbix.ethparser.web3.ContractConstants.D6;
import static pro.belbix.ethparser.web3.ContractConstants.D8;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Vaults {

    public static final String YCRV_V0 = "0xF2B223Eb3d2B382Ead8D85f3c1b7eF87c1D35f3A".toLowerCase();
    public static final String WETH_V0 = "0x8e298734681adbfC41ee5d17FF8B0d6d803e7098".toLowerCase();
    public static final String USDC_V0 = "0xc3F7ffb5d5869B3ade9448D094d81B0521e8326f".toLowerCase();
    public static final String USDT_V0 = "0xc7EE21406BB581e741FBb8B21f213188433D9f2F".toLowerCase();
    public static final String DAI_V0 = "0xe85C8581e60D7Cd32Bbfd86303d2A4FA6a951Dac".toLowerCase();
    public static final String WBTC_V0 = "0xc07EB91961662D275E2D285BdC21885A4Db136B0".toLowerCase();
    public static final String RENBTC_V0 = "0xfBe122D0ba3c75e1F7C80bd27613c9f35B81FEeC".toLowerCase();
    public static final String CRVRENWBTC_V0 = "0x192E9d29D43db385063799BC239E772c3b6888F3".toLowerCase();
    public static final String UNI_ETH_DAI_V0 = "0x1a9F22b4C385f78650E7874d64e442839Dc32327".toLowerCase();
    public static final String UNI_ETH_USDC_V0 = "0x63671425ef4D25Ec2b12C7d05DE855C143f16e3B".toLowerCase();
    public static final String UNI_ETH_USDT_V0 = "0xB19EbFB37A936cCe783142955D39Ca70Aa29D43c".toLowerCase();
    public static final String UNI_ETH_WBTC_V0 = "0xb1FeB6ab4EF7d0f41363Da33868e85EB0f3A57EE".toLowerCase();
    public static final String UNI_ETH_DAI = "0x307E2752e8b8a9C29005001Be66B1c012CA9CDB7".toLowerCase();
    public static final String UNI_ETH_USDC = "0xA79a083FDD87F73c2f983c5551EC974685D6bb36".toLowerCase();
    public static final String UNI_ETH_USDT = "0x7DDc3ffF0612E75Ea5ddC0d6Bd4e268f70362Cff".toLowerCase();
    public static final String UNI_ETH_WBTC = "0x01112a60f427205dcA6E229425306923c3Cc2073".toLowerCase();
    public static final String WETH = "0xFE09e53A81Fe2808bc493ea64319109B5bAa573e".toLowerCase();
    public static final String USDC = "0xf0358e8c3CD5Fa238a29301d0bEa3D63A17bEdBE".toLowerCase();
    public static final String USDT = "0x053c80eA73Dc6941F518a68E2FC52Ac45BDE7c9C".toLowerCase();
    public static final String DAI = "0xab7FA2B2985BCcfC13c6D86b1D5A17486ab1e04C".toLowerCase();
    public static final String WBTC = "0x5d9d25c7C457dD82fc8668FFC6B9746b674d4EcB".toLowerCase();
    public static final String RENBTC = "0xC391d1b08c1403313B0c28D47202DFDA015633C4".toLowerCase();
    public static final String CRVRENWBTC = "0x9aA8F427A17d6B0d91B6262989EdC7D45d6aEdf8".toLowerCase();
    public static final String SUSHI_WBTC_TBTC = "0xF553E1f826f42716cDFe02bde5ee76b2a52fc7EB".toLowerCase();
    public static final String YCRV = "0x0FE4283e0216F94f5f9750a7a11AC54D3c9C38F3".toLowerCase();
    public static final String _3CRV = "0x71B9eC42bB3CB40F017D8AD8011BE8e384a95fa5".toLowerCase();
    public static final String TUSD = "0x7674622c63Bee7F46E86a4A5A18976693D54441b".toLowerCase();
    public static final String CRV_TBTC = "0x640704D106E79e105FDA424f05467F005418F1B5".toLowerCase();
    public static final String PS = "0x25550Cccbd68533Fa04bFD3e3AC4D09f9e00Fc50".toLowerCase();
    public static final String PS_V0 = "0x59258F4e15A5fC74A7284055A8094F58108dbD4f".toLowerCase();
    public static final String CRV_CMPND = "0x998cEb152A42a3EaC1f555B1E911642BeBf00faD".toLowerCase();
    public static final String CRV_BUSD = "0x4b1cBD6F6D8676AcE5E412C78B7a59b4A1bbb68a".toLowerCase();
    public static final String CRV_USDN = "0x683E683fBE6Cf9b635539712c999f3B3EdCB8664".toLowerCase();
    public static final String SUSHI_ETH_DAI = "0x203E97aa6eB65A1A02d9E80083414058303f241E".toLowerCase();
    public static final String SUSHI_ETH_USDC = "0x01bd09A1124960d9bE04b638b142Df9DF942b04a".toLowerCase();
    public static final String SUSHI_ETH_USDT = "0x64035b583c8c694627A199243E863Bb33be60745".toLowerCase();
    public static final String SUSHI_ETH_WBTC = "0x5C0A3F55AAC52AA320Ff5F280E77517cbAF85524".toLowerCase();
    public static final String IDX_ETH_DPI = "0x2a32dcbb121d48c106f6d94cf2b4714c0b4dfe48".toLowerCase();
    public static final String CRV_HUSD = "0x29780C39164Ebbd62e9DDDE50c151810070140f2".toLowerCase();
    public static final String CRV_HBTC = "0xCC775989e76ab386E9253df5B0c0b473E22102E2".toLowerCase();

    public final static Map<String, String> vaultNames = new LinkedHashMap<>();
    public final static Map<String, Double> vaultDividers = new LinkedHashMap<>();
    public final static Set<String> lpTokens = new LinkedHashSet<>();
    public final static Map<String, String> vaultNameToOldVaultName = new LinkedHashMap<>();

    static {
        try {
            initMaps();
        } catch (Exception e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }

        vaultDividers.put(YCRV_V0, D18);
        vaultDividers.put(WETH_V0, D18);
        vaultDividers.put(USDC_V0, D6);
        vaultDividers.put(USDT_V0, D6);
        vaultDividers.put(DAI_V0, D18);
        vaultDividers.put(WBTC_V0, D8);
        vaultDividers.put(RENBTC_V0, D8);
        vaultDividers.put(CRVRENWBTC_V0, D18);
        vaultDividers.put(UNI_ETH_DAI_V0, D18);
        vaultDividers.put(UNI_ETH_USDC_V0, D18);
        vaultDividers.put(UNI_ETH_USDT_V0, D18);
        vaultDividers.put(UNI_ETH_WBTC_V0, D18);
        vaultDividers.put(UNI_ETH_DAI, D18);
        vaultDividers.put(UNI_ETH_USDC, D18);
        vaultDividers.put(UNI_ETH_USDT, D18);
        vaultDividers.put(UNI_ETH_WBTC, D18);
        vaultDividers.put(WETH, D18);
        vaultDividers.put(USDC, D6);
        vaultDividers.put(USDT, D6);
        vaultDividers.put(DAI, D18);
        vaultDividers.put(WBTC, D8);
        vaultDividers.put(RENBTC, D8);
        vaultDividers.put(CRVRENWBTC, D18);
        vaultDividers.put(SUSHI_WBTC_TBTC, D18);
        vaultDividers.put(YCRV, D18);
        vaultDividers.put(_3CRV, D18);
        vaultDividers.put(TUSD, D18);
        vaultDividers.put(CRV_TBTC, D18);
        vaultDividers.put(PS, D18);
        vaultDividers.put(PS_V0, D18);
        vaultDividers.put(CRV_CMPND, D18);
        vaultDividers.put(CRV_BUSD, D18);
        vaultDividers.put(CRV_USDN, D18);
        vaultDividers.put(SUSHI_ETH_DAI, D18);
        vaultDividers.put(SUSHI_ETH_USDC, D18);
        vaultDividers.put(SUSHI_ETH_USDT, D18);
        vaultDividers.put(SUSHI_ETH_WBTC, D18);
        vaultDividers.put(IDX_ETH_DPI, D18);
        vaultDividers.put(CRV_HUSD, D18);
        vaultDividers.put(CRV_HBTC, D18);

        lpTokens.add("UNI_ETH_DAI");
        lpTokens.add("UNI_ETH_USDC");
        lpTokens.add("UNI_ETH_USDT");
        lpTokens.add("UNI_ETH_WBTC");
        lpTokens.add("SUSHI_WBTC_TBTC");
        lpTokens.add("UNI_ETH_DAI_V0");
        lpTokens.add("UNI_ETH_USDC_V0");
        lpTokens.add("UNI_ETH_USDT_V0");
        lpTokens.add("UNI_ETH_WBTC_V0");
        lpTokens.add("SUSHI_ETH_DAI");
        lpTokens.add("SUSHI_ETH_USDC");
        lpTokens.add("SUSHI_ETH_USDT");
        lpTokens.add("SUSHI_ETH_WBTC");
        lpTokens.add("IDX_ETH_DPI");

        vaultNameToOldVaultName.put("UNI_ETH_DAI", "UNI_ETH_DAI_V0");
        vaultNameToOldVaultName.put("UNI_ETH_USDC", "UNI_ETH_USDC_V0");
        vaultNameToOldVaultName.put("UNI_ETH_USDT", "UNI_ETH_USDT_V0");
        vaultNameToOldVaultName.put("UNI_ETH_WBTC", "UNI_ETH_WBTC_V0");
        vaultNameToOldVaultName.put("WETH", "WETH_V0");
        vaultNameToOldVaultName.put("USDC", "USDC_V0");
        vaultNameToOldVaultName.put("USDT", "USDT_V0");
        vaultNameToOldVaultName.put("DAI", "DAI_V0");
        vaultNameToOldVaultName.put("WBTC", "WBTC_V0");
        vaultNameToOldVaultName.put("RENBTC", "RENBTC_V0");
        vaultNameToOldVaultName.put("CRVRENWBTC", "CRVRENWBTC_V0");

    }

    //dangerous, but useful
    private static void initMaps() throws IllegalAccessException, NoSuchFieldException {
        for (Field field : Vaults.class.getDeclaredFields()) {
            if (!(field.get(null) instanceof String)) {
                continue;
            }
            String vaultName = field.getName();
            if (vaultName.startsWith("_")) {
                vaultName = vaultName.replaceFirst("_", "");
            }
            vaultNames.put((String) field.get(null), vaultName);
        }
    }

}
