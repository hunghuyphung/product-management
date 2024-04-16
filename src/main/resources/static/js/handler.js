function handleDisplayDialog(id) {
    const modal = document.getElementById(id);
    const computedStyle = window.getComputedStyle(modal);

    modal.style.display = computedStyle.display === 'none' ? 'flex' : 'none';
}

function handleDisplayUserMenu() {
    const userMenu = document.getElementById('user-menu');
    const computedStyle = window.getComputedStyle(userMenu);

    userMenu.style.display = computedStyle.display === 'none' ? 'block' : 'none';
}

// display toast
function handleDisplayNotificationToast() {
    const notificationToast = document.getElementById('notification-toast');
    const computedStyle = window.getComputedStyle(notificationToast);

    if (computedStyle.display !== 'none') {
        notificationToast.style.display = 'none';
    }
}

setTimeout(handleDisplayNotificationToast, 3000);


// display email by username
function updateEmail() {
    const usernameInput = document.getElementById('su-username');
    const emailInput = document.getElementById('su-email');
    emailInput.value = usernameInput.value + "@vnpay.vn";
}

//hide error message
function handleErrorMsg() {
    const errorMsgList = document.querySelectorAll('.error-message-js');
    errorMsgList.forEach(err => {
        const computedStyle = window.getComputedStyle(err);

        if (computedStyle.display !== 'none') {
            err.style.display = 'none';
        }
    });
}

setTimeout(handleErrorMsg, 3000);
