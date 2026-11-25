document.addEventListener('DOMContentLoaded', () => {
   
    const navLinks = document.querySelectorAll('.nav-links a, .logo');
    const sections = document.querySelectorAll('main section');
    const menuItems = document.querySelectorAll('.nav-links a');

    function showSection(targetId) {
        if (!targetId) return;

        sections.forEach((sec) => {
            sec.classList.toggle('hidden', sec.id !== targetId);
        });

        menuItems.forEach((link) => {
            link.classList.toggle('active', link.getAttribute('data-section') === targetId);
        });
    }

    navLinks.forEach((link) => {
        link.addEventListener('click', (event) => {
            event.preventDefault();
            const targetId = link.getAttribute('data-section');
            showSection(targetId);
        });
    });

   
    const form = document.getElementById('repairOrderForm');
    const resultBox = document.getElementById('orderResult');
    const generatedCodeEl = document.getElementById('generatedCode');

    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        const payload = {
            customer: {
                fullName: document.getElementById('clientName').value.trim(),
                phone: document.getElementById('clientPhone').value.trim(),
                email: document.getElementById('clientEmail').value.trim() || null
            },
            vehicle: {
                brand: document.getElementById('carBrand').value.trim(),
                model: document.getElementById('carModel').value.trim(),
                licensePlate: document.getElementById('licensePlate').value.trim().toUpperCase(),
                vin: document.getElementById('vin').value.trim().toUpperCase(),
                mileage: parseInt(document.getElementById('mileage').value, 10)
            },
            issueDescription: document.getElementById('issueDescription').value.trim()
        };

        try {
        

            console.log('Wysyłane dane (JSON):', payload);
            const mockCode = 'AFX-' + Math.floor(10000 + Math.random() * 90000);
            generatedCodeEl.textContent = mockCode;

            resultBox.classList.remove('hidden');
            form.reset();
        } catch (error) {
            alert('Wystąpił błąd podczas tworzenia zgłoszenia: ' + error.message);
        }
    });

    
    const historyForm = document.getElementById('historySearchForm');
    const historyResult = document.getElementById('historyResult');
    const historyQueryText = document.getElementById('historyQueryText');

    historyForm.addEventListener('submit', (event) => {
        event.preventDefault();
        const query = document.getElementById('historyQuery').value.trim().toUpperCase();
        historyQueryText.textContent = query;
        historyResult.classList.remove('hidden');
    });

 
    const clientForm = document.getElementById('clientLookupForm');
    const clientStatusResult = document.getElementById('clientStatusResult');
    const clientCodeDisplay = document.getElementById('clientCodeDisplay');

    clientForm.addEventListener('submit', (event) => {
        event.preventDefault();
        const code = document.getElementById('trackingInput').value.trim().toUpperCase();
        clientCodeDisplay.textContent = code;
        clientStatusResult.classList.remove('hidden');
    });
});
