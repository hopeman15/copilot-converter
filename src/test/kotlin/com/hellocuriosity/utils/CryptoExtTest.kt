package com.hellocuriosity.utils

import kotlin.test.Test
import kotlin.test.assertEquals

private typealias TestableCrypto = Pair<String?, String?>

class CryptoExtTest {
    private val data: List<TestableCrypto> =
        listOf(
            TestableCrypto(null, null),
            TestableCrypto("not convertable", "not convertable"),
            TestableCrypto("Aleph Zero", "ALEPH"),
            TestableCrypto("Algorand", "ALGO"),
            TestableCrypto("Amp", "AMP"),
            TestableCrypto("Ankr", "ANKR"),
            TestableCrypto("Cosmos", "ATOM"),
            TestableCrypto("Basic Attention Token", "BAT"),
            TestableCrypto("Bitcoin Cash", "BCH"),
            TestableCrypto("Bitcoin", "BTC"),
            TestableCrypto("Celo Gold", "CGLD"),
            TestableCrypto("Compound", "COMP"),
            TestableCrypto("Dash", "DASH"),
            TestableCrypto("EOS", "EOS"),
            TestableCrypto("Ethereum", "ETH"),
            TestableCrypto("Ethereum 2.0", "ETH2"),
            TestableCrypto("Euro", "EUR"),
            TestableCrypto("Fetch.ai", "FET"),
            TestableCrypto("The Graph", "GRT"),
            TestableCrypto("Litecoin", "LTC"),
            TestableCrypto("Maker", "MKR"),
            TestableCrypto("NEAR Protocol", "NEAR"),
            TestableCrypto("Oraculos", "OXT"),
            TestableCrypto("Rally", "RLY"),
            TestableCrypto("Ronin", "RONIN"),
            TestableCrypto("The Sandbox", "SAND"),
            TestableCrypto("USD Coin", "USDC"),
            TestableCrypto("Vara", "VARA"),
            TestableCrypto("VeChain", "VET"),
            TestableCrypto("Stellar", "XLM"),
            TestableCrypto("Tezos", "XTZ"),
            TestableCrypto("Zcash", "ZEC"),
            TestableCrypto("ZetaChain", "ZETA"),
            TestableCrypto("0x", "ZRX"),
        )

    @Test
    fun `Crypto abbreviation should convert to full name`() {
        data.forEach { (expected, given) ->
            assertEquals(expected, given.toCryptoName())
        }
    }
}
