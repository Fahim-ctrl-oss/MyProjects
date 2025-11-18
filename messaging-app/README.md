# Node + Socket.io Project Setup

This project demonstrates a simple Node.js server using **Express** and **Socket.io** with **Nodemon** for automatic server restarts during development.

---

## **Prerequisites**

* [Node.js](https://nodejs.org/) installed
* npm (comes with Node.js)
* Basic knowledge of JavaScript

---

## **Setup Instructions**

### 1. Initialize the Project

```bash
npm init -y
```

This creates a `package.json` file with default settings.

---

### 2. Install Dependencies

Install **Express** and **Socket.io**:

```bash
npm install express socket.io
```

Install **Nodemon** as a dev dependency (for automatic server restarts):

```bash
npm install --save-dev nodemon
```

---

### 3. Create Scripts in `package.json`

Add the following scripts in `package.json`:

```json
"scripts": {
  "start": "node server.js",
  "dev": "nodemon server.js"
}
```

* `start`: Run the server normally
* `dev`: Run the server with Nodemon (auto-restarts on file changes)

---

### 4. Start the Server

Run the server in development mode:

```bash
npm run dev
```

You should see output like:

```
[nodemon] 2.0.22
[nodemon] watching path(s): *.*
[nodemon] starting `node server.js`
Server running on http://localhost:3000
```

---

### 5. Notes

* Make sure you have a `server.js` file at the root of your project.
* Nodemon watches your files and automatically restarts the server whenever you save changes.
* For production, use:

```bash
npm start
```
