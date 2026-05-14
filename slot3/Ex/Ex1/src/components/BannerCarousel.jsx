import Carousel from "react-bootstrap/Carousel";
import { listBanner } from "../data/banner";

function BannerCarousel() {
  return (
    <section id="home" className="banner-section">
      <Carousel fade interval={2500}>
        {listBanner.map((banner) => (
          <Carousel.Item key={banner.id}>
            <img
              className="d-block w-100 banner-image"
              src={banner.image}
              alt={banner.title}
            />
            <Carousel.Caption>
              <h2>{banner.title}</h2>
              <p>{banner.description}</p>
            </Carousel.Caption>
          </Carousel.Item>
        ))}
      </Carousel>
    </section>
  );
}

export default BannerCarousel;
