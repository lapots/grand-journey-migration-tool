const fs = require('fs');
const _ = require('lodash');

const dataPath = '../resources/data/';

fs.readdir(dataPath, (err, filenames) => {
    if (err) { throw err; }

    let truePaths = _.map(filenames, filename => dataPath + filename);
    _.each(truePaths, filename => {
        fs.readFile(filename, 'utf-8', (err, data) => {
            if (err) { throw err; }

            const jsn = JSON.parse(data); // json

            const generatorName = jsn.name;
            const generatorParts = jsn.name_parts;

            console.log(generatorName);
            console.log(generatorParts);
        })
    });
});

