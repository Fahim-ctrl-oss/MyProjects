import { Routes, Route, Navigate } from 'react-router-dom'
import HomePage from '@/pages/HomePage/HomePage'
import LoginPage from './pages/LoginPage/LoginPage'
import NotFoundPage from '@/pages/NotFoundPage/NotFoundPage'
import UserProfilePage from './pages/UserProfilePage/UserProfilePage'
import { useAuth } from './context/Authentication/useAuth'

const RequireAuth = ({ children }: { children: React.JSX.Element }) => {
  const { user } = useAuth();
  return user ? children : <Navigate to="/login" replace />;
};

const App = () => (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/profile-page" element={<RequireAuth><UserProfilePage /></RequireAuth>} />
      <Route path="*" element={<NotFoundPage/>} />
    </Routes>
);

export default App;
