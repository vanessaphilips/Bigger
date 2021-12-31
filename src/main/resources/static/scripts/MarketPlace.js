// import {rootURL} from "./URLs.js"
// import {getToken} from "./DummyLogin.js"
// import  "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"

"use strict"

const assetDivList = document.getElementById("assetListContainer")

await getToken()
let token = localStorage.getItem("jwtToken")


class Asset {
    constructor(code, name, currentPrice) {
        this.name = name
        this.code = code
        this.currentPrice = currentPrice
    }
}

class PriceHistory {
    constructor(date, price, asset) {
        this.date = date
        this.price = price
        this.asset = asset
    }
}

//Declare functions

const createTradeButton = (asset) => {
    let tradeButton = document.createElement("label")
    tradeButton.className = "tradeButton"
    tradeButton.id = asset.code + "Button"
    tradeButton.innerHTML = "trade"
    tradeButton.addEventListener("click", function () {
        localStorage.setItem("currentAsset", JSON.stringify(asset))
        window.location.href = tradeButtonLink;
    })
    return tradeButton
}

function setInnerHtml(assetCodeLabel, assetNameLabel, assetCurrentPriceLabel, asset) {
    assetCodeLabel.innerHTML = asset.code
    assetNameLabel.innerHTML = asset.name
    assetCurrentPriceLabel.innerHTML = asset.currentPrice
}

const createDivPerAsset = (priceHistoriesOfAsset) => {
    let assetDivElement = document.createElement("div");
    if (priceHistoriesOfAsset.length > 0) {
        const asset = priceHistoriesOfAsset[0].asset

        let assetCodeLabel = document.createElement("label");
        let assetNameLabel = document.createElement("label");
        let graphContainer = document.createElement("div")
        let assetCurrentPriceLabel = document.createElement("label");
        let priceHistoryGraph = createGraph(priceHistoriesOfAsset)

        graphContainer.id = "graphContainer"
        assetDivElement.id = asset.code;
        assetCodeLabel.id = "code"
        assetNameLabel.id = "name"
        assetCurrentPriceLabel.id = "currentPrice"
        priceHistoryGraph.id = "priceHistory" + asset.code
        priceHistoryGraph.className = "priceHistoryGraph"
        assetDivElement.className = "asset"
        graphContainer.appendChild(priceHistoryGraph)
        setInnerHtml(assetCodeLabel, assetNameLabel, assetCurrentPriceLabel, asset)

        assetDivElement.appendChild(assetCodeLabel)
        assetDivElement.appendChild(assetNameLabel)
        assetDivElement.appendChild(assetCurrentPriceLabel)
        assetDivElement.appendChild(graphContainer)
        assetDivElement.appendChild(createTradeButton(asset))

    }
    return assetDivElement
}

const setHtmlElementAssetList = (priceHistoriesByAssets) => {
    for (const priceHistoriesOfAsset of priceHistoriesByAssets) {
        assetDivList.appendChild(createDivPerAsset(priceHistoriesOfAsset))
    }
}

function parseDate(localDateTimeString) {
    const dateTime = localDateTimeString.substring(0, 10) + " " + localDateTimeString.substring(11)
    return new Date(dateTime)
}

function jsonToPriceHistoriesByAssets(json) {
    const priceHistoriesByAssets = []
    for (const priceHistoriesOfAssetJson of json) {
        const priceHistoriesOfAsset = []
        for (const priceHistory of priceHistoriesOfAssetJson) {
            priceHistoriesOfAsset.push(new PriceHistory(parseDate(priceHistory.dateTime), priceHistory.price, priceHistory.asset))
        }
        priceHistoriesByAssets.push(priceHistoriesOfAsset)
    }
    return priceHistoriesByAssets
}

function createDateInPast(monthsBack) {
    const date = new Date()
    date.setMonth(date.getMonth() - monthsBack)
    return date.toISOString().substring(0, 23);
}

function acceptHeaders(token) {
    const accept = []
    accept.push(['Accept', 'Application/json'])
    accept.push(["content-type", "application/json"])
    accept.push(['Access-Control-Allow-Origin', '*'])
    accept.push(['Access-Control-Allow-Methods', '*'])
    accept.push(['authorization', token])
    return accept
}

const getPriceHistoriesByAsset = (token) => {
    return fetch(`${rootURL}priceHistories`,
        {
            method: 'POST',
            headers: acceptHeaders(token),
            body: createDateInPast(1)
        }).then(promise => {
        if (promise.ok) {
            return promise.json()
        } else {
            console.log("Couldn't retrieve pricehistory from the server")
            // return promise
        }
    }).then(json =>
        json
    )
}


const jsonPriceHistories = await getPriceHistoriesByAsset(token)
if (jsonPriceHistories !== undefined) {
    const priceHistoriesByAssets = jsonToPriceHistoriesByAssets(jsonPriceHistories)
    setHtmlElementAssetList(priceHistoriesByAssets)
}



