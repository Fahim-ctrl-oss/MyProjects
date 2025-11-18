import http from 'http';
import { Server } from 'socket.io';
import app from './src/app.js';
import socketHandler from './src/sockets/index.js';
import { clearDB, connectDB, disconnectDB, initDB } from './db.js'; 
import  User  from "./models/User.js";

const PORT = process.env.PORT || 3000;

const server = http.createServer(app);

async function startServer() {
  try {
    const db = await connectDB();
    await clearDB(db);

    const io = new Server(server, {
      cors: {
        origin: process.env.FRONTEND_URL || '*',
        methods: ['GET', 'POST'],
      },
    });
  
    socketHandler(io);

    server.listen(PORT, () => console.log(`Server running on http://localhost:${PORT}`));

    await initDB(db);
    const playerModel = new User(db);
    await playerModel.create('Alice', 'admin');
    await playerModel.create('Bob', 'player');
    const players = await playerModel.findAll();
    console.log(players);


    const shutdown = async (signal) => {
      io.close(() => console.log('Socket.IO closed'));
      server.close(() => console.log('HTTP server closed'));
      await disconnectDB();
      process.exit(0);
    };

    process.on('SIGINT', () => shutdown('SIGINT'));
    process.on('SIGTERM', () => shutdown('SIGTERM'));

  } catch (err) {
    console.error('Failed to start server:', err);
    process.exit(1);
  }
}

startServer();