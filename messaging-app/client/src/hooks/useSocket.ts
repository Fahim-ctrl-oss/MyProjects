import { useEffect, useRef, useState } from "react";
import { io, Socket } from "socket.io-client";

export interface ServerToClientEvents {
  update: (data: Record<string, unknown>) => void;
}

export interface ClientToServerEvents {
  "chat message": (data: { player: string; dice: number }) => void;
  "join room": (data: { player: string; roomId: number }) => void;
  "leave room": (data: { player: string; roomId: number }) => void;
}

export const useSocket = (url: string) => {
  const socketRef = useRef<Socket<ServerToClientEvents, ClientToServerEvents> | null>(null);
  const [connected, setConnected] = useState(false);

  useEffect(() => {
    const socket: Socket<ServerToClientEvents, ClientToServerEvents> =
      io(url, { transports: ["websocket", "polling"] });

    socketRef.current = socket;

    socket.on("connect", () => {
      console.log("Connected as", socket.id);
      setConnected(true);
    });

    socket.on("update", (data) => {
      console.log("Update received:", data);
    });

    return () => {
      socket.disconnect();
      socketRef.current = null;
    };
  }, [url]);

  return { socketRef, connected };
};
