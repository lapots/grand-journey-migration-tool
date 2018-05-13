package com.lapots.breed.migration.util

class DataUtil {

    /*
        From
            {
                generator: {
                    part1: [item1],
                    part2: [item2]
                ]
            }
        To
            [generator, part1, item1],
            [generator, part2, item2]
     */
    static def transformJsonData(json) {
        def data = []
        json.each { generator ->
            def part_group_name = generator.key
            generator.value.each { part ->
                def part_name = part.key
                part.value.each { value ->
                    data << ["part_group_name": part_group_name, "part_name": part_name, "part_value": value]
                }
            }
        }
        data
    }

}
