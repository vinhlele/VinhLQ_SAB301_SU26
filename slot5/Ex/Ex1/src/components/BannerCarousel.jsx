import Carousel from 'react-bootstrap/Carousel';
import listBanner from '../data/banner';

function BannerCarousel() {
  return (
    <Carousel>
      {listBanner.map((banner) => (
        <Carousel.Item key={banner.id}>
          <img
            className="d-block w-100"
            src={banner.image}
            height="500"
            style={{ objectFit: 'cover' }}
            alt="banner"
          />
        </Carousel.Item>
      ))}
    </Carousel>
  );
}

export default BannerCarousel;