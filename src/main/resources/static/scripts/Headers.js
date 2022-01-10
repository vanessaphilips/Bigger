function acceptHeadersWithToken(token) {
    const accept = []
    accept.push(['Accept', 'Application/json'])
    accept.push(["content-type", "application/json"])
    accept.push(['Access-Control-Allow-Origin', '*'])
    accept.push(['Access-Control-Allow-Methods', '*'])
    accept.push(['authorization', token])
    return accept
}
function acceptHeaders() {
    const accept = []
    accept.push(['Accept', 'Application/json'])
    accept.push(["content-type", "application/json"])
    accept.push(['Access-Control-Allow-Origin', '*'])
    accept.push(['Access-Control-Allow-Methods', '*'])
    return accept
}