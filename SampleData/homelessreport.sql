USE petfinderdb;

INSERT INTO homelessreport
(contact, reportdate, status, priority, message, animaltype, healthcondition, location, aggressive, indanger)
VALUES
('1111111111', NOW(), 0, 0, 'Test Message', 'Dog', 'FAIR', 'Boston, MA 02116', 1, 1),
('2222222222', NOW(), 0, 2, 'Test Message', 'Dog', 'FAIR', '800 Boylston Street, TS#101A, Boston, MA 02199', 1, 1),

('3333333333', NOW(), 0, 1, 'Test Message', 'Dog', 'FAIR', '333 Massachusetts Ave, Boston, MA 02118', 1, 1),
('1231231231', NOW(), 0, 2, 'Test Message', 'Dog', 'FAIR', '2300 Washington St, Roxbury, MA 02119', 1, 1),

('2344354567', NOW(), 0, 1, 'Test Message', 'Dog', 'FAIR', '270 Babcock Street C-102, Boston, MA 02215', 1, 1),
('5645677865', NOW(), 0, 0, 'Test Message', 'Dog', 'FAIR', '1153 Commonwealth Avenue, Allston, MA 02134', 1, 1),

('7865464357', NOW(), 0, 0, 'Test Message', 'Dog', 'FAIR', '395 Harvard St # A, Brookline, MA 02446', 1, 1),
('9086785674', NOW(), 0, 2, 'Test Message', 'Dog', 'FAIR', '1269 Beacon Street, Brookline, MA 02446, Brookline, MA 02446', 1, 1),

('9785634564', NOW(), 0, 1, 'Test Message', 'Dog', 'FAIR', '45 Francis St, Boston, MA 02115', 1, 1),
('4357654378', NOW(), 0, 0, 'Test Message', 'Dog', 'FAIR', '680 Parker St, Roxbury Crossing, MA 02120', 1, 1)