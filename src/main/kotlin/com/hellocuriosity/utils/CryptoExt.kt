package com.hellocuriosity.utils

@Suppress("CyclomaticComplexMethod")
fun String?.toCryptoName(): String? =
    when (this) {
        "ALEPH" -> "Aleph Zero"
        "ALGO" -> "Algorand"
        "AMP" -> "Amp"
        "ANKR" -> "Ankr"
        "ATOM" -> "Cosmos"
        "BAT" -> "Basic Attention Token"
        "BCH" -> "Bitcoin Cash"
        "BTC" -> "Bitcoin"
        "CGLD" -> "Celo Gold"
        "COMP" -> "Compound"
        "DASH" -> "Dash"
        "EOS" -> "EOS"
        "ETH" -> "Ethereum"
        "ETH2" -> "Ethereum 2.0"
        "EUR" -> "Euro"
        "FET" -> "Fetch.ai"
        "GRT" -> "The Graph"
        "LTC" -> "Litecoin"
        "MKR" -> "Maker"
        "NEAR" -> "NEAR Protocol"
        "OXT" -> "Oraculos"
        "RLY" -> "Rally"
        "RONIN" -> "Ronin"
        "SAND" -> "The Sandbox"
        "USDC" -> "USD Coin"
        "XLM" -> "Stellar"
        "XTZ" -> "Tezos"
        "ZEC" -> "Zcash"
        "ZRX" -> "0x"
        else -> this
    }
