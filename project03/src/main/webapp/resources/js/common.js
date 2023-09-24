/* progress bar */
$('[data-background]').each(function () {
    $(this).css({
        'background-image': 'url(' + $(this).data('background') + ')'
    });
});

$('[data-color]').each(function () {
    $(this).css({
        'background-color': $(this).data('color')
    });
});

$('[data-progress]').each(function () {
    $(this).css({
        'bottom': $(this).data('progress')
    });
});