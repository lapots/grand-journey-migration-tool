const fs = require('fs');
const dir = require('node-dir');
const pgp = require('pg-promise');

const connection = {
    user: '',
    password: '',
    port: ,
    database: '',
    host: ''
};

const db = pgp(connection);
const cs = new pgp.helpers.ColumnSet(
[
    defCol('part_group_name'),
    defCol('part_name'),
    defCol('part_value'),
    defCol('part_order'),
    defCol('precede_space')
], {table: 'generator.generators'});

dir.readFiles('../resources/data/', { match: /\.json$/ }, (err, content, next) => {
    if (err) throw err;

    const batch = []
    const jsn = JSON.parse(content);
    const generatorName = jsn.name;
    const generatorParts = jsn.name_parts;

    Object.entries(generatorParts).forEach(([key, value]) => {
        const pr_sp = value.precede_space;
        const ordr = value.order;
        const nm_prts = value.parts;

        nm_prts.forEach(item => {
            batch.push({part_group_name: generatorName, part_name: key, part_value: item, part_order: ordr, precede_space: pr_sp});
        });

    });

    console.log(batch);
    const insert = pgp.helpers.insert(batch, cs);
    db.none(insert)
        .then(() => { console.log('successfully added records')})
        .catch(error => { console.log(error); });

    next();
});
