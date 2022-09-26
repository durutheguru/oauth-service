
module.exports = {

    patchObject : function(obj, patch) {
        for (var key in patch) {
            if (patch.hasOwnProperty(key)) {
                obj[key] = patch[key];
            }
        }

        return obj;
    },

    mergePathRoot : function(root, paths) {
        for (var i = 0, l = paths.length; i < l; i++) {
            paths[i] = root + paths[i];
        }

        return paths;
    }

};

