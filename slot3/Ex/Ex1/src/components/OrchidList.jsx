import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import { orchids } from "../data/orchidsData";

function OrchidList() {
  return (
    <main id="orchids" className="orchid-section">
      <Container>
        <div className="section-heading">
          <h1>List of Orchids</h1>
          <p>Explore popular orchid varieties from the collection.</p>
        </div>

        <Row className="g-4">
          {orchids.map((orchid) => (
            <Col key={orchid.id} xs={12} sm={6} lg={3}>
              <Card className="orchid-card h-100">
                <Card.Img variant="top" src={orchid.image} alt={orchid.name} />
                <Card.Body className="d-flex flex-column">
                  <Card.Title>{orchid.name}</Card.Title>
                  <Card.Text>{orchid.description}</Card.Text>
                  <div className="orchid-meta mt-auto">
                    <span>{orchid.origin}</span>
                    <strong>{orchid.price}</strong>
                  </div>
                  <Button variant="primary" className="mt-3">
                    Detail
                  </Button>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
      </Container>
    </main>
  );
}

export default OrchidList;
