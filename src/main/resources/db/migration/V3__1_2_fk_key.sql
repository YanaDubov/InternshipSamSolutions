ALTER TABLE localtest
ADD CONSTRAINT fk_local_id_test FOREIGN KEY (id_test)
REFERENCES test(id_test) ON UPDATE CASCADE ON DELETE CASCADE;