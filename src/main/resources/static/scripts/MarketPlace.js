"use strict"
//Declare constants

const MARKETPLACE_ROOT_CONTAINER = document.getElementById("assetListContainer")
const TRADEBUTTON_CLASS = "tradeButton"
const TRADEBUTTON_INNERHTML = "trade"
const TRADEBUTTON_ID_SUFFIX = "Button"

const ASSETCODELABEL_ID = "code"
const ASSETNAMELABEL_ID = "name"
const ASSETCURRENTPRICELABEL_ID = "price"

const PRICEHISTORYGRAPH_CLASS = "priceHistoryGraph"
const PRICEHISTORYGRAPH_ID_PREFIX = "priceHistory"

const GRAPHCONTAINER_CLASS = "graphContainer"
const GRAPHCONTAINER_ID_PREFIX = "graphContainer"

const ASSETCONTAINER_CLASS = "assetContainer"
const SELECTED_ASSETCONTAINER_CLASS = "selectedAsset"
const DAYSBACKINPUTFIELD_ID = "daysBack"
const ROUNDING_DIGITS = 2


await getToken()
let token = localStorage.getItem(JWT_KEY)
let daysBackInputField = document.getElementById(DAYSBACKINPUTFIELD_ID);
let daysBack = 30
daysBackInputField.value = daysBack;
//Declare functions

    function
updatePriceHistoryGraphs(priceHistoriesByAssets)
{
    for (const priceHistoriesOfAsset of priceHistoriesByAssets) {
        const asset = priceHistoriesOfAsset[0].asset
        let priceHistoryContainer = document.getElementById(GRAPHCONTAINER_ID_PREFIX + asset.code)
        while (priceHistoryContainer.firstChild) {
            priceHistoryContainer.removeChild(priceHistoryContainer.firstChild);
        }
        let priceHistoryGraph = createPriceHistoryGraph(asset, priceHistoriesOfAsset)
        priceHistoryContainer.appendChild(priceHistoryGraph)
    }
}

daysBackInputField.addEventListener("input", async () => {
    console.log("field is veranderd")
    daysBack = daysBackInputField.value
    let json = await getPriceHistoriesByAsset(token)
    const jsonPriceHistories = JSON.parse(json.priceHistory)
    if (jsonPriceHistories !== undefined) {
        updatePriceHistoryGraphs(jsonToPriceHistoriesByAssets(jsonPriceHistories));
    }
})


const createTradeButton = (asset) => {
    let tradeButton = document.createElement("label")
    tradeButton.className = TRADEBUTTON_CLASS
    tradeButton.id = asset.code + TRADEBUTTON_ID_SUFFIX
    tradeButton.innerHTML = TRADEBUTTON_INNERHTML
    tradeButton.addEventListener("click", function () {
        localStorage.setItem(CURRENT_ASSET_KEY, JSON.stringify(asset))
        window.location.href = tradeButtonLink;
    })
    return tradeButton
}

function creatAssetCodeLabel(asset) {
    let assetCodeLabel = document.createElement("label");
    assetCodeLabel.id = ASSETCODELABEL_ID
    assetCodeLabel.innerHTML = asset.code
    return assetCodeLabel
}

function creatAssetNameLabel(asset) {
    let assetNameLabel = document.createElement("label");
    assetNameLabel.id = ASSETNAMELABEL_ID
    assetNameLabel.innerHTML = asset.name
    return assetNameLabel
}

function normalizePrice(currentPrice) {
    //is de prijs ver achter de komma? Dan wordt 1/currentPrice groot
    //Hoe ver achter de komma? neem er de log10 van en rond de factor af.
    let factor = Math.round(Math.log10(1 / currentPrice))
    // als de factor groter dan 1 is (dus getal is meer dan een nul achter de komma)
    //corrigeer het aantal digits achter de komma met die factor. Als factor kleiner dan 1 is rond dan af op het
    //standaard aantal cijfers achter de komma(ROUNDING_DIGITS)
    let multiPly = (factor > 1 ? Math.pow(10, factor) : 1) * Math.pow(10, ROUNDING_DIGITS)
    return (Math.round(currentPrice * multiPly) / multiPly)

}

function creatCurrentPriceLabel(asset) {
    let assetcurrentPriceLabel = document.createElement("label");
    assetcurrentPriceLabel.id = ASSETCURRENTPRICELABEL_ID
    assetcurrentPriceLabel.innerHTML = normalizePrice(asset.currentPrice)
    return assetcurrentPriceLabel
}

function createPriceHistoryGraph(asset, priceHistoriesOfAsset) {
    let priceHistoryGraph = createGraph(priceHistoriesOfAsset)
    priceHistoryGraph.id = PRICEHISTORYGRAPH_ID_PREFIX + asset.code
    priceHistoryGraph.className = PRICEHISTORYGRAPH_CLASS
    return priceHistoryGraph;
}

function creatGraphContainer(asset, priceHistoriesOfAsset) {
    let graphContainer = document.createElement("div");
    let priceHistoryGraph = createPriceHistoryGraph(asset, priceHistoriesOfAsset);
    graphContainer.appendChild(priceHistoryGraph)
    graphContainer.id = GRAPHCONTAINER_ID_PREFIX + asset.code
    graphContainer.className = GRAPHCONTAINER_CLASS
    return graphContainer
}

const createAssetContainer = (priceHistoriesOfAsset) => {
    let assetContainer = document.createElement("div");
    if (priceHistoriesOfAsset.length > 0) {
        const asset = priceHistoriesOfAsset[0].asset
        assetContainer.id = asset.code;
        assetContainer.className = ASSETCONTAINER_CLASS
        assetContainer.appendChild(creatAssetCodeLabel(asset))
        assetContainer.appendChild(creatAssetNameLabel(asset))
        assetContainer.appendChild(creatCurrentPriceLabel(asset))
        assetContainer.appendChild(creatGraphContainer(asset, priceHistoriesOfAsset))
        assetContainer.appendChild(createTradeButton(asset))
    }
    assetContainer.addEventListener("click", () => {
        if (assetContainer.className === SELECTED_ASSETCONTAINER_CLASS) {
            assetContainer.className = ASSETCONTAINER_CLASS
        } else {
            for (const assetContainer of MARKETPLACE_ROOT_CONTAINER.getElementsByClassName(SELECTED_ASSETCONTAINER_CLASS)) {
                assetContainer.className = ASSETCONTAINER_CLASS
            }
            assetContainer.className = SELECTED_ASSETCONTAINER_CLASS
            console.log(assetContainer.className)
        }

    })
    return assetContainer
}

const fillPageWithAssets = (priceHistoriesByAssets) => {

    for (const priceHistoriesOfAsset of priceHistoriesByAssets) {
        priceHistoriesByAssets.sort(c => c.date)
        MARKETPLACE_ROOT_CONTAINER.appendChild(createAssetContainer(priceHistoriesOfAsset))
    }
}

function parseDate(localDateTimeString) {
    const dateTime = localDateTimeString.substring(0, 10) + " " + localDateTimeString.substring(11)
    return new Date(dateTime)
}

function jsonToPriceHistoriesByAssets(jsonPriceHistories) {
    const priceHistoriesByAssets = []
    for (const priceHistoriesOfAssetJson of jsonPriceHistories) {
        const priceHistoriesOfAsset = []
        for (const priceHistory of priceHistoriesOfAssetJson) {
            priceHistoriesOfAsset.push(new PriceHistory(parseDate(priceHistory.dateTime), priceHistory.price, priceHistory.asset))
        }
        priceHistoriesByAssets.push(priceHistoriesOfAsset)
    }
    return priceHistoriesByAssets
}

function createDateInPast() {
    const date = new Date(new Date().valueOf() - daysBack * 86400000)
    return date.toISOString().substring(0, 23);
}



const getPriceHistoriesByAsset = (token) => {
    return fetch(`${rootURL}marketplace`,
        {
            method: 'POST',
            headers: acceptHeadersWithToken(token),
            body: createDateInPast()
        }).then(promise => {
        if (promise.ok) {
            return promise.json()
        } else {
            console.log("Couldn't retrieve pricehistory from the server")
        }
    }).then(json => json)
}
let updateInterval = 300000;
let lastUpdate = Date.now();

async function refreshpage() {
    console.log("pageUpdated on " + new Date(Date.now()).toLocaleTimeString())
    const json = await getPriceHistoriesByAsset(token)
    updateInterval = Number.parseFloat(json.updateInterval);
    const jsonPriceHistories = JSON.parse(json.priceHistory);
    if (jsonPriceHistories !== undefined) {
        const priceHistoriesByAssets = jsonToPriceHistoriesByAssets(jsonPriceHistories)
        lastUpdate = priceHistoriesByAssets[0].sort(c => c.date)[0].date
        fillPageWithAssets(priceHistoriesByAssets)
    }
}

await refreshpage()
const dateNow = new Date()
const initialTimeOut = updateInterval - (dateNow.getTime() - lastUpdate.getTime()) + 10000
console.log(initialTimeOut)
const onPriceHistoryUpdate = () => {
    refreshpage()
    setInterval(refreshpage, updateInterval)
}

setTimeout(onPriceHistoryUpdate, initialTimeOut)

