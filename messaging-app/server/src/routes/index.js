import express from 'express';
const router = express.Router();

// API endpoint
router.get('/', (req, res) => {
    res.json({ status: 'Server is running' });
});

export default router;  
