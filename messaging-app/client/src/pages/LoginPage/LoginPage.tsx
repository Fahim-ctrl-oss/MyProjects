// src/pages/LoginPage.tsx
import { useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '@/context/Authentication/useAuth';
import { useState } from 'react';

interface LocationState {
  from?: {
    pathname: string;
  };
}

const LoginPage = () => {
  const navigate = useNavigate();
  const location = useLocation() as { state?: LocationState };
  const { login } = useAuth();

  const [username, setUsername] = useState('JohnDoe');

  const from = location.state?.from?.pathname || '/';

  const handleLogin = () => {
    if (!username) return alert('Enter a username');
    login(username);
    navigate(from, { replace: true });
  };

  return (
    <div>
      <h1>Login</h1>
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={e => setUsername(e.target.value)}
      />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
};

export default LoginPage;
