module.exports = async function (context, req) {
    return {
        target: 'newStrokes',
        arguments: [ req.body ]
    };
};