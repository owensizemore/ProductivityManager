function openPage() {
    console.log("interjecting");
    browser.tabs.create({
        "url": "/testing.html"
    });
}

browser.browserAction.onClicked.addListener(openPage);