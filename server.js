const express = require("express");
const cors = require("cors");
const cookieSession = require("cookie-session");

const dbConfig = require("./app/config/db.config");
const app = express();

const translate = require('translate-google');
const GoogleImages = require('google-images');
const client = new GoogleImages('1519a03fdcfc8450a', 'AIzaSyDENRPI8j4SWLxvhjnNIW5cDN7Ejhbqu_o');
app.use(cors());
var languages = require('language-list')();
// parse requests of content-type - application/json
app.use(express.json());
//1519a03fdcfc8450a

//AIzaSyDENRPI8j4SWLxvhjnNIW5cDN7Ejhbqu_o

// parse requests of content-type - application/x-www-form-urlencoded
app.use(express.urlencoded({ extended: true }));

app.use(
  cookieSession({
    name: "bezkoder-session",
    secret: "COOKIE_SECRET", // should use as secret environment variable
    httpOnly: true
  })
);

const db = require("./app/models");
const Role = db.role;

db.mongoose
  .connect(`mongodb+srv://superadmin:jadore@cluster0.8ra1aqb.mongodb.net/team-srh?retryWrites=true&w=majority`, {
    useNewUrlParser: true,
    useUnifiedTopology: true
  })
  .then(() => {
    console.log("Successfully connect to MongoDB.");
    initial();
  })
  .catch(err => {
    console.error("Connection error", err);
    process.exit();
  });

// simple route
app.get("/", (req, res) => {
  res.json({ message: "Welcome to bezkoder application." });
});

app.post('/api/translate', (req, res) => {
  let fromLang = req.body.from;
  let toLang = req.body.to;
  let term = req.body.term;
  translate(term, { from: fromLang, to: toLang }).then(data => {
    let resObj = {
      fromLang: fromLang,
      toLang: toLang,
      term: data
    }
    console.log(resObj)
    res.send(resObj);
  }).catch(err => {
    console.error(err)
  })
});
app.get("/api/languages", (req, res) => {
  res.json(languages.getData())
})

app.post('/api/images', (req, res) => {
  try {
    client.search(req.body.text)
      .then(images => {
        res.send(images);
      });
  } catch (err) {
    res.json(err);
  }
 
})
// routes
require("./app/routes/auth.routes")(app);
require("./app/routes/user.routes")(app);

// set port, listen for requests
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});

function initial() {
  Role.estimatedDocumentCount((err, count) => {
    if (!err && count === 0) {
      new Role({
        name: "user"
      }).save(err => {
        if (err) {
          console.log("error", err);
        }

        console.log("added 'user' to roles collection");
      });

      new Role({
        name: "moderator"
      }).save(err => {
        if (err) {
          console.log("error", err);
        }

        console.log("added 'moderator' to roles collection");
      });

      new Role({
        name: "admin"
      }).save(err => {
        if (err) {
          console.log("error", err);
        }

        console.log("added 'admin' to roles collection");
      });
    }
  });
}
