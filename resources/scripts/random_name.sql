create or replace function generator.generateName(gnr_type varchar(128))
returns varchar(512) as $$
declare
    parts varchar(128)[] := array(
        select distinct part_name from generator.generators
        where part_group_name = gnr_type
    );
    part varchar (128);
    randomName varchar(512) := '';
begin
    foreach part in array parts
    loop
        randomName := randomName || generator.randompartvalue(gnr_type, part);
    end loop;
    return randomName;
end;
$$ language plpgsql
