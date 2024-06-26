const express = require('express');
const User = require('../model/User');
const router = express.Router();
const jwt = require('jsonwebtoken')

const timeout = 3600

// Função para gerar um Token JWT
const generateToken = (params = {}, timeout = 3600) => {
    return jwt.sign(params, process.env.JWT_SECRET, {expiresIn: timeout} )
}

router.post("/", async (req, res) => {
    const {email, password} = req.body

    // Verificar se o usuário existe no banco de dados
    const user = await User.findOne({email, password})

    // Verificar as credenciais do usuário
    if(!user) 
        return res.status(400).json({message: "Credenciais invalidas!"})

    const now = new Date()

    // Se passar gerar o Token
    const resposta = {
        token: generateToken({id: user.id}),
        user,
        loggedId: now,
        expiresId: new Date(now.getTime() + timeout * 1000)
    }

    // Devolver a resposta para o cliente
    return res.json(resposta)
})



module.exports = router