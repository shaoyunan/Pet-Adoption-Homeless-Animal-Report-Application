USE petfinderdb;

INSERT INTO pet
(petname, petage, animaltype, petownerid, gender, color, description, breed, location, listed)
VALUES
('Ben', 1, 'Dog', 1, 'MALE', 'Black', 'description here', 'German Shepherd', 'Rollins Pl, Boston, MA 02114', true),
('Brode', 2, 'Cat', 1, 'FEMALE', 'White', 'description here', 'British Shorthair', '171 Milk St, Boston, MA 02109', true),

('Chris', 3, 'Dog', 2, 'FEMALE', 'Black', 'description here', 'German Shepherd', '108 Union Wharf, Boston, MA 02109', true),
('Wilson', 4, 'Cat', 2, 'MALE', 'White', 'description here', 'American Bobtail', '180 Mt Vernon St, Dorchester, MA 02125',true),

('Hideo', 5, 'Dog', 3, 'MALE', 'Black', 'description here', 'American Leopard Hound', '74 Sudan St, Dorchester, MA 02125',true),
('Kojima', 1, 'Cat', 3, 'FEMALE', 'White', 'description here', 'British Shorthair', '225 W Squantum St #100, Quincy, MA 02171',true),

('Sid', 2, 'Dog', 4, 'FEMALE', 'Black', 'description here', 'German Shepherd', '170 Centre St, Milton, MA 02186',true),
('Meier', 3, 'Cat', 4, 'MALE', 'White', 'description here', 'British Shorthair', '465 Huntington Ave, Boston, MA 02115',true),

('Gabe', 4, 'Dog', 5, 'MALE', 'Black', 'description here', 'American Leopard Hound', '2300 Washington St, Roxbury, MA 02119',true),
('Newell', 5, 'Cat', 5, 'FEMALE', 'White', 'description here', 'American Shorthair', '1130 Dorchester Ave, Dorchester, MA 02125',true);

