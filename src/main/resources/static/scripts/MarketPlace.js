import {rootURL} from "./Root.js"
import {getToken} from "./DummyLogin.js"
"use strict"

const assetList = document.getElementById("assetListContainer")
//
// window.onload = () => {
//     assetsJson()
// }
class Asset {

    constructor(code, name, currentValue) {
        this.name = name
        this.code = code
        this.currentValue = currentValue
    }


}
const gotoTransaction = (asset) => {
     fetch(`${rootURL}trade`, {
        method: "POST",
        headers: {
            Accept: 'Application/json',
            "content-type": "application/json",
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': '*',
             'X-Content-Type-Options': '*',
            'authorization': `test`
        },
        body: JSON.stringify(asset)
    }).then(response => {
        if (response.ok) {
            return response.json()

        } else {

        }
    }).then(json=>{
             assetList.innerText = json.code
     })
}

const createTradeButton = (asset) => {
    let tradeButton = document.createElement("button")
    tradeButton.className = "tradeButton"
    tradeButton.id= asset.code+"Button"
    tradeButton.innerHTML = "trade"
    tradeButton.addEventListener("click",function(){
        gotoTransaction(new Asset(asset.code, asset.name, asset.currentValue))
    })
    return tradeButton
}


const createAssetDiv = (jsonAsset) => {
    //console.log(jsonAsset.code)

    let assetDivElement = document.createElement("div");
    let assetCode = document.createElement("label");
    let assetName = document.createElement("label");
    let assetCurrentPrice = document.createElement("label");

    assetDivElement.id = jsonAsset.code;
    assetCode.id = "code"
    assetName.id = "name"
    assetCurrentPrice.id = "currentPrice"

    assetCode.innerHTML = jsonAsset.code
    assetName.innerHTML = jsonAsset.name
    assetCurrentPrice.innerHTML = jsonAsset.currentPrice
    assetDivElement.appendChild(assetCode)
    assetDivElement.appendChild(assetName)
    assetDivElement.appendChild(assetCurrentPrice)
    assetDivElement.appendChild(createTradeButton(jsonAsset))
    assetDivElement.className = "asset"
   // console.log(assetDivElement)
    return assetDivElement
}

const createAssetView = (jsonAssets) => {
   // console.log(jsonAssets)
    for (const jsonAsset of jsonAssets) {
        assetList.appendChild(createAssetDiv(jsonAsset))
    }
}

const assetsJson = (token) => {



    console.log("token in assetsJson" +token)
    fetch(`${rootURL}marketplace`,
        {
            method: "GET",
            headers: {
                Accept: 'Application/json',
                "content-type": "application/json",
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': '*',
                'X-Content-Type-Options': '*',
                'authorization': token
            }
        }).then(promise => {
        if (promise.ok) {
            return promise.json()
        } else {
            console.log(promise.error())
        }
    }).then(json => {
            createAssetView(json)
        }
    ).catch(reason =>
        console.log(reason)
    )
}
let token = await getToken()
console.log(token)
assetsJson(token)


