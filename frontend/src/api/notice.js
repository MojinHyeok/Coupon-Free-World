export function connect(data) {
  var ws = new WebSocket('ws://localhost:8078/replyEcho')
  let socket = ws
  // 접속이 되면
  ws.onopen = function() {
    // 접속되자마자 보낸다.
    console.log('info:connection opend')
    console.log('발사!')
    socket.send(data)
  }
  // 서버로 부터 메시지가 오면 호출
  ws.onmessage = function(event) {
    console.log('ReceiveMessage:', event.data + '\n')
  }
  // 서버와 접속이 끊기면 호출
  ws.onclose = function() {
    console.log('Info: connection closed.')
  }
  ws.onerror = function(err) {
    console.log('Error:', err)
  }
}
