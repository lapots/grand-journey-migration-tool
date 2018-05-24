const fs = require('fs');
const _ = require('lodash'); // no uses for now
const dir = require('node-dir');

const { Pool, Client } = require('pg');
const pool = new Pool();

const dataPath = '../resources/data/';

dir.readFiles(dataPath, { match: /\.json$/ }, (err, content, next) => {
    if (err) throw err;

    const jsn = JSON.parse(content);
    const generatorName = jsn.name;
    const generatorParts = jsn.name_parts;

    Object.entries(generatorParts).forEach(([key, value]) => {
        const pr_sp = value.precede_space;
        const ordr = value.order;
        const nm_prts = value.parts;

        nm_prts.forEach(item => {
            console.log(
                `part_group_name: ${generatorName}; part_name: ${key}; part_value: ${item}; part_order:${ordr}; precede_space: ${pr_sp}`
            );
        });
    });

    next();
});

