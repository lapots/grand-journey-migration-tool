create or replace function generator.randomPartValue(gnr_type varchar(128), pn varchar(128))
returns varchar(128) as $$
begin
    return (
        select part_value from generator.generators
        where part_group_name = gnr_type and part_name = pn
        order by random()
        limit 1
    );
end;
$$ language plpgsql
