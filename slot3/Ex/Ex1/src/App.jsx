import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import MyNavbar from "./components/MyNavbar";
import BannerCarousel from "./components/BannerCarousel";
import OrchidList from "./components/OrchidList";

function App() {
  return (
    <div className="app">
      <MyNavbar />
      <BannerCarousel />
      <OrchidList />
    </div>
  );
}

export default App;
