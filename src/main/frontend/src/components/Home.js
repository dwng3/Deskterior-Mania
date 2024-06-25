import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div>
      <h1>Home</h1>
      <p>가장 먼저 보여지는 페이지입니다.</p>
      <Link to="/login">login</Link>
      <Link to="/signup">signup</Link>
      <Link to="/postWrite">postWrite</Link>

    </div>
  );
}
