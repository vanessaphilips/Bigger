const navigation = document.getElementById("navigation")
const currentContentContainer = document.getElementById("currentContentContainer");
const navElements =  {}

const stringToHTML = function (str) {
    const parser = new DOMParser();
    const doc = parser.parseFromString(str, 'text/html');
    return doc.body;
}


function fillNavMap() {
    navElements.marketplace = stringToHTML('<object data="MarketPlace.html"  id="currentContentObject"></object>')
    navElements.register = stringToHTML('<object data="Registration.html"  id="currentContentObject"></object>')
    navElements.login = stringToHTML('<object  data="LoginPage.html"  id="currentContentObject"></object>')
}

function setCurrentContent(selectedContent) {
    console.log(currentContentContainer)
    if (currentContentContainer.firstChild !== undefined) {
        console.log(selectedContent)
        currentContentContainer.replaceChild(selectedContent, currentContentContainer.firstChild)
    } else {
        currentContentContainer.appendChild(selectedContent)
    }
}

function fillNavigation() {
    for (const navKey in navElements) {
        const navLink = document.createElement("label")
        navLink.innerText = navKey;
        navLink.addEventListener("click", () => {
            console.log("in FillNavigation: "+navElements[navKey])
            setCurrentContent(navElements[navKey])
        })
        navigation.appendChild(navLink)
    }
}


fillNavMap()
fillNavigation()
setCurrentContent(navElements['login'])
// console.log(htmlPage)
// document.replaceChild(document.body, htmlPage.body)