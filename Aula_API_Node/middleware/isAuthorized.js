const jwt = require("jsonwebtoken");

const isAuthorized = (req, res, next) => {
    // Obter o Token
    const { authorization } = req.headers;

    if (!authorization) {
        return res.status(403).json({ message: 'Sem Token' });
    }

    const token = authorization.split(' ')[1]; // Remove "Bearer " do token

    // Validar o Token
    jwt.verify(token, process.env.JWT_SECRET, (err, decoded) => { // Use 'token' aqui
        if (err) {
            return res.status(401).json({ message: 'Token inválido' });
        }

        console.log(decoded);
        req.userId = decoded.id; // Insere os dados do token na requisição
        next(); // Chama o próximo middleware na cadeia de execução
    });
};

module.exports = isAuthorized;
