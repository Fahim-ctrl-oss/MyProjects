import http from 'http';
import { Server } from 'socket.io';
import app from './src/app.js';
import socketHandler from './src/sockets/index.js';

const PORT = 3000;
const server = http.createServer(app);

const io = new Server(server, {
  cors: { origin: '*', methods: ['GET', 'POST'] }
});

socketHandler(io);

server.listen(PORT, () => console.log(`Server running on http://localhost:${PORT}`));
