let marsApiButtons = document.querySelectorAll("button[id*='marsApi']");

marsApiButtons.forEach(button => button.addEventListener('click', function () {
                                    const buttonId = this;
                                    const roverId = this.textContent;
                                    let apiData = document.getElementById('marsApiRoverData');
                                    apiData.value = roverId;
                                    document.getElementById('frmRoverType').submit();
                                 }))


