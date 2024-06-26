const express = require('express');
const Evento = require('../model/Evento');
const isAuthorized = require('../middleware/isAuthorized');
const router = express.Router();

// ROTAS
router.get('/', isAuthorized, async function(req, res) {
  return res.json(await Evento.find());
});

// Obter um evento por id
router.get("/:id", isAuthorized, async (req, res) => {
  const { id } = req.params;
  const result = await Evento.findById(id);

  return result ? res.json(result) : res.status(401).send();
});

router.post("/", isAuthorized, async (req, res) => {
    const body = req.body;
    const evento = new Evento(body);
    const hasErrors = evento.validateSync();

    return hasErrors ? res.status(400).json(hasErrors) : res.status(201).json(await evento.save());
});

router.put("/:id", isAuthorized, async (req, res) => {
  const { id } = req.params;
  const eventData = req.body;

  try {
    const updatedEvent = await Evento.findByIdAndUpdate(id, eventData, { new: true });

    if (!updatedEvent) {
      return res.status(404).json({ error: "Event not found" });
    }

    res.json(updatedEvent);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

router.delete("/:id", isAuthorized, async (req, res) => {
    const { id } = req.params;

    try {
      const deletedEvent = await Evento.findByIdAndDelete(id);

      if (!deletedEvent) {
        return res.status(404).json({ error: "Event not found" });
      }

      res.json({ message: "Event deleted successfully" });
    } catch (error) {
      res.status(500).json({ error: "Internal Server Error" });
    }
});

// EXPORTA DO MÓDULO
module.exports = router;
