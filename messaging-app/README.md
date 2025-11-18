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

---

# React Frontend Setup (Vite + TypeScript)

### Create Vite Project

```bash
npm create vite@latest client
cd client
```

* **Why:** This scaffolds a new React project with Vite, which is fast and optimized for modern frontends.
* Choose **TypeScript** when prompted to get type safety and autocompletion.

### Configure Vite Path Aliases

Edit `vite.config.ts`:

```ts
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'path';

export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "src")
    }
  }
});
```

* **Why:**

  * `@` alias lets you import modules like `@/context/Authentication/AuthProvider` instead of using long relative paths (`../../../`).
  * Improves code readability and makes refactoring easier.
  * Vite uses this for runtime module resolution.

### Update `tsconfig.app.json`

Add/replace inside `"compilerOptions"`:

```json
{
  "compilerOptions": {
    "composite": true,
    "baseUrl": "./src",
    "paths": {
      "@/*": ["*"]
    },
    "ignoreDeprecations": "6.0"
  },
  "include": ["src"]
}
```

* **Why:**

  * `"composite": true` → Required because the root `tsconfig.json` references this project. Without it, TS will throw errors about project references.
  * `"baseUrl": "./src"` → Tells TypeScript to resolve non-relative imports starting from `src/`.
  * `"paths": { "@/*": ["*"] }` → Enables `@/` alias in TypeScript (matches the Vite alias).
  * `"ignoreDeprecations": "6.0"` → Silences warnings about `baseUrl` deprecation in TS 7+.
  * `"include": ["src"]` → Ensures TS checks all files in the `src` folder.

### Update `tsconfig.node.json`

Add/replace inside `"compilerOptions"`:

```json
{
  "compilerOptions": {
    "composite": true
  },
  "include": ["vite.config.ts"]
}
```

* **Why:**

  * The root `tsconfig.json` references this file. TypeScript requires **all referenced projects to have `composite: true`**.
  * This file is mainly for tooling (Vite, Node), so no other settings are needed.


### Start Development Server

```bash
npm install
npm run dev
```

* **Why:**

  * Installs all dependencies (`npm install`).
  * Starts Vite’s dev server (`npm run dev`) with Fast Refresh and HMR (hot module reload).
  * Your app will be available at `http://localhost:5173/`.

### Key Benefits of This Setup

1. **TypeScript support** → static type checking, autocompletion, and fewer runtime bugs.
2. **`@/` path aliases** → cleaner imports, easier to refactor.
3. **Fast Refresh** → React components update instantly in the browser.
4. **Project references (`composite: true`)** → scales better for monorepos or multi-package projects.
5. **Vite optimizations** → fast builds and dev server.

---
