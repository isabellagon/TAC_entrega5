const express = require('express');
const User = require('../model/User');
const isAuthorized = require('../middleware/isAuthorized');
const router = express.Router();

//ROTAS
/* GET users listing. */
router.get('/', isAuthorized, async function(req, res) {
  return res.json(await User.find())
});

//Obter um usuário por id
router.get("/:id", isAuthorized, async (req, res) => {
  const {id} = req.params

  const result = await User.findById(id)

  return result
    ? res.json(result)
    : res.status(401).send()
})

router.post("/", async (req, res) => {
    const body = req.body;
    const user = new User(body)
    const hasErrors = user.validateSync()

    return hasErrors
      ? res.status(400).json(hasErrors)
      : res.status(201).json(await user.save())
    
})

router.put("/:id", isAuthorized, async (req, res) => {
  const { id } = req.params;
  const body = req.body;

  try {
      const updatedUser = await User.findByIdAndUpdate(id, body, { new: true });

      if (!updatedUser) {
          return res.status(404).json({ message: "Usuário não encontrado" });
      }

      res.json(updatedUser);
  } catch (error) {
      res.status(500).json({ message: error.message });
  }
});


router.delete("/:id", isAuthorized, async (req, res) => {
  const { id } = req.params;

  try {
      const deletedUser = await User.findByIdAndDelete(id);

      if (!deletedUser) {
          return res.status(404).json({ message: "Usuário não encontrado" });
      }

      res.json({ message: "User deleted successfully" });
  } catch (error) {
      res.status(500).json({ message: error.message });
  }
});


//EXPORTA DO MODULO
module.exports = router;
