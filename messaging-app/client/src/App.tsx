import { useSocket } from "./hooks/useSocket";
import { joinRoom, leaveRoom } from "./utils/socketActions"


const App = () => {
  const { socketRef, connected } = useSocket("http://localhost:3000");
  return (
    <div style={{ padding: 20, display: "flex", flexDirection: "column", maxWidth: "500px" }}>
      <h1>Client</h1>

      <button onClick={() => joinRoom(socketRef.current)}>
        {connected ? "Join Room" : "Connecting..."}
      </button>
      <button onClick={() => leaveRoom(socketRef.current)}>
        {connected ? "Leave Room" : "Connecting..."}
      </button>

      
    </div>
  );
};

export default App;
