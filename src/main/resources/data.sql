INSERT INTO public.users (created_at, modified_at, "version", email, "name", "password")
VALUES (
  NOW(),
  NOW(),
  0,
  'teste12345@gmail.com',
  'teste12345@gmail.com',
  '$2a$10$5HZe3oCwpgsJDDN5HdEL4u90N/EI/GUIvDtjOkreIBWZendJ8QRqW'
);

INSERT INTO public.truck (created_at, modified_at, "version", plate, truck_type)
VALUES (
  NOW(),
  NOW(),
  0,
  'ABC1234',
  'Caminhão de transporte'
);

INSERT INTO public.box (created_at, modified_at, "version", box_number)
VALUES (
  NOW(),
  NOW(),
  0,
  'CAIXA123'
);



