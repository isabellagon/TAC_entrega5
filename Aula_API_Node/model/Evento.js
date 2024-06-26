const mongoose = require("mongoose")

const EventoSchema = new mongoose.Schema(
    {
        nome: {type: String, require: true},
        tipo: {type: String, require: true},
        dispositivoID: {type: Number, require: true},
        dataHora: {type: Date, default: Date.now},
        detalhes: {type: String, default: false}
    }
)

module.exports = mongoose.model('Evento', EventoSchema, 'evento')