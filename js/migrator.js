const fs = require('fs');
const _ = require('lodash'); // no uses for now
const dir = require('node-dir');

const dataPath = '../resources/data/';

dir.readFiles(dataPath, { match: /\.json$/ }, (err, content, next) => {
    if (err) throw err;

    const jsn = JSON.parse(content);
    const generatorName = jsn.name;
    const generatorParts = jsn.name_parts;

    Object.entries(generatorParts).forEach(([key, value]) => {
        value.forEach(item => {
            console.log(`gnr: ${generatorName}; gnr_p: ${key}; gnr_p_v: ${item}`);
        });
    });

    next();
});

