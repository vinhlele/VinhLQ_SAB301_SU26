import React, { useState } from 'react'

import {
  Row,
  Col,
  Container,
  Card,
  Button
} from 'react-bootstrap'

import Modal from 'react-bootstrap/Modal';

import { OrchidsData } from '../shared/ListOfOrchids';

function Orchids() {

  const [show, setShow] = useState(false);

  const [selectedOrchid, setSelectedOrchid] = useState(null);

  const handleClose = () => setShow(false);

  const handleShow = (orchid) => {
    setSelectedOrchid(orchid);
    setShow(true);
  }

  return (

    <Container className="mt-4">

      <Row>

        {OrchidsData.map((orchid) => (

          <Col md={3} className="mb-4" key={orchid.id}>

            <Card>

              <Card.Img
                variant="top"
                src={orchid.image}
                height="250"
                style={{ objectFit: 'cover' }}
              />

              <Card.Body>

                <Card.Title>
                  {orchid.orchidName}
                </Card.Title>

                <Card.Text>
                  {orchid.category}
                </Card.Text>

                <Button
                  variant="primary"
                  onClick={() => handleShow(orchid)}
                >
                  Detail
                </Button>

              </Card.Body>

            </Card>

          </Col>

        ))}

      </Row>

      <Modal show={show} onHide={handleClose}>

        <Modal.Header closeButton>

          <Modal.Title>
            {selectedOrchid?.orchidName}
          </Modal.Title>

        </Modal.Header>

        <Modal.Body>

          {selectedOrchid && (

            <div>

              <img
                src={selectedOrchid.image}
                alt={selectedOrchid.orchidName}
                style={{ width: '100%' }}
              />

              <p className="mt-3">
                {selectedOrchid.description}
              </p>

              <p>
                Category: {selectedOrchid.category}
              </p>

              <p>
                Special:
                {selectedOrchid.isSpecial ? " Yes" : " No"}
              </p>

            </div>

          )}

        </Modal.Body>

        <Modal.Footer>

          <Button
            variant="secondary"
            onClick={handleClose}
          >
            Close
          </Button>

        </Modal.Footer>

      </Modal>

    </Container>
  )
}

export default Orchids;