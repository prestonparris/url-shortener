const form = document.getElementById('url_form');
const shortenedLink = document.getElementById('shortened_url');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const longUrl = document.getElementById('url_input').value;

    updateShortUrl(longUrl);
});

function updateShortUrl(longUrl) {
    const endpoint = `http://localhost:8080`;

    const data = { longUrl: longUrl };

    fetch(endpoint,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then((response) => response.json())
        .then((data) => {
            if (data.success) {
                displayShortUrl(data.url);
            } else {
                console.error('Error: something went wrong while attempting to create a shortened url');
            }

        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function displayShortUrl(shortUrl) {
    const url = "http://localhost:8080/" + shortUrl;
    shortenedLink.innerHTML = `<a href="${url}">${url}</a>`;
}




