-- Insertion de données dans la table citoyen
INSERT INTO citoyen (idcin, nom, prenom,dateNaissance, adresse)
VALUES
    ('CIN001', 'Doe', 'John' , '2023-08-08', '13 Rue Lenine Ankadifotsy'),
    ('CIN002', 'Smith', 'Jane', '09-08-2023', '19 Rue Lenine Ankadifotsy'),
    ('CIN003', 'Brown', 'Michael',  '08-10-2023', '14 Rue Lenine Ankadifotsy');

-- Insertion de données dans la table allergie
INSERT INTO allergie (allergie)
VALUES
    ('Allergie aux noix'),
    ('Allergie au pollen'),
    ('Allergie au gluten');
-- Insertion de données dans la table citoyen_allergie
INSERT INTO citoyen_allergie (idcin, idAllergie)
VALUES
    ('CIN001', 1),
    ('CIN001', 2),
    ('CIN001', 3),
    ('CIN002', 1),
    ('CIN002', 2),
    ('CIN002', 3),
    ('CIN003', 1),
    ('CIN003', 2),
    ('CIN003', 3);

-- Insertion de données dans la table partie_corps
INSERT INTO partie_corps (nomPartieCorps)
VALUES
    ('Tete'),
    ('Bras'),
    ('Jambe');

-- Insertion de données dans la table operation
INSERT INTO operation (idPartieCorps, dateOperation, idcin)
VALUES
    (1, '2023-09-15', 'CIN001'),
    (2, '2023-08-20', 'CIN002'),
    (3, '2023-09-10', 'CIN003');

-- Insertion de données dans la table maladie
INSERT INTO maladie (nomMaladie)
VALUES
    ('Diabete'),
    ('Asthme'),
    ('Hypertension');

-- Insertion de données dans la table citoyen_maladie
INSERT INTO citoyen_maladie (idcin, idMaladie)
VALUES
    ('CIN001', 1),
    ('CIN002', 2),
    ('CIN003', 3);
