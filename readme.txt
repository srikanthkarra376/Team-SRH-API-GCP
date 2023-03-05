

Création du système d'authentif sur mogodb.
1- Création de la table ainsi que la collection roles:
2- Execution du scripte suivant :
db.roles.insertMany([   { name: "ROLE_USER" },   { name: "ROLE_CONTENT_EDITOR" },   { name: "ROLE_TUTOR" },   { name: "ROLE_ADMIN" },
])
3 - exécution du script suivant :
db.createUser({
user: "superadmin",
pwd: "jadore",
roles: [ { role: "userAdminAnyDatabase", db: "admin" },
{ role: "dbAdminAnyDatabase", db: "admin" },
{ role: "readWriteAnyDatabase", db: "admin" } ]
})