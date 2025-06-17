// API Configuration
const API_BASE_URL = 'http://localhost:8080/api';
const QUESTIONS_ENDPOINT = `${API_BASE_URL}/questions`;

// Application state
let questions = [];
let isEditing = false;

// DOM Elements
const addQuestionForm = document.getElementById('addQuestionForm');
const editQuestionForm = document.getElementById('editQuestionForm');
const editSection = document.getElementById('editSection');
const questionsContainer = document.getElementById('questionsContainer');
const loadingIndicator = document.getElementById('loadingIndicator');
const messageContainer = document.getElementById('messageContainer');
const refreshButton = document.getElementById('refreshQuestions');
const cancelEditButton = document.getElementById('cancelEdit');

// Initialize the application
document.addEventListener('DOMContentLoaded', function() {
    initializeApp();
});

function initializeApp() {
    // Set up event listeners
    addQuestionForm.addEventListener('submit', handleAddQuestion);
    editQuestionForm.addEventListener('submit', handleEditQuestion);
    refreshButton.addEventListener('click', loadQuestions);
    cancelEditButton.addEventListener('click', cancelEdit);
    
    // Load initial data
    loadQuestions();
}

// API Functions
async function apiCall(url, options = {}) {
    try {
        showLoading(true);
        const response = await fetch(url, {
            headers: {
                'Content-Type': 'application/json',
                ...options.headers
            },
            ...options
        });
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const contentType = response.headers.get('content-type');
        if (contentType && contentType.includes('application/json')) {
            return await response.json();
        } else {
            return await response.text();
        }
    } catch (error) {
        console.error('API call failed:', error);
        showMessage('An error occurred while communicating with the server', 'error');
        throw error;
    } finally {
        showLoading(false);
    }
}

async function getAllQuestions() {
    try {
        const result = await apiCall(QUESTIONS_ENDPOINT);
        return Array.isArray(result) ? result : [];
    } catch (error) {
        console.error('Error fetching questions:', error);
        return [];
    }
}

async function getQuestionById(id) {
    return await apiCall(`${QUESTIONS_ENDPOINT}/${id}`);
}

async function addQuestion(questionData) {
    return await apiCall(QUESTIONS_ENDPOINT, {
        method: 'POST',
        body: JSON.stringify(questionData)
    });
}

async function updateQuestion(id, questionData) {
    return await apiCall(`${QUESTIONS_ENDPOINT}/${id}`, {
        method: 'PUT',
        body: JSON.stringify(questionData)
    });
}

async function deleteQuestion(id) {
    return await apiCall(`${QUESTIONS_ENDPOINT}/${id}`, {
        method: 'DELETE'
    });
}

// UI Functions
function showLoading(show) {
    loadingIndicator.style.display = show ? 'block' : 'none';
}

function showMessage(message, type = 'info') {
    const messageElement = document.createElement('div');
    messageElement.className = `message message-${type}`;
    messageElement.textContent = message;
    
    messageContainer.appendChild(messageElement);
    
    // Auto-remove message after 5 seconds
    setTimeout(() => {
        if (messageElement.parentNode) {
            messageElement.parentNode.removeChild(messageElement);
        }
    }, 5000);
}

function renderQuestions() {
    if (questions.length === 0) {
        questionsContainer.innerHTML = `
            <div class="empty-state">
                <h3>No questions found</h3>
                <p>Start by adding your first question above.</p>
                <button class="btn btn-primary" onclick="document.getElementById('questionTitle').focus()">
                    Add First Question
                </button>
            </div>
        `;
        return;
    }
    
    questionsContainer.innerHTML = questions.map(question => `
        <div class="question-card" data-id="${question.id}">
            <div class="question-header">
                <div class="question-title">${escapeHtml(question.title)}</div>
                <div class="question-id">ID: ${question.id}</div>
            </div>
            <div class="question-actions">
                <button class="btn btn-edit" onclick="editQuestion(${question.id})">
                    Edit
                </button>
                <button class="btn btn-danger" onclick="confirmDeleteQuestion(${question.id})">
                    Delete
                </button>
                <button class="btn btn-secondary" onclick="viewQuestion(${question.id})">
                    View Details
                </button>
            </div>
        </div>
    `).join('');
}

function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

// Event Handlers
async function handleAddQuestion(event) {
    event.preventDefault();
    
    const formData = new FormData(event.target);
    const title = formData.get('title').trim();
    
    if (!title) {
        showMessage('Please enter a question title', 'error');
        return;
    }
    
    try {
        const questionData = { title };
        await addQuestion(questionData);
        
        showMessage('Question added successfully!', 'success');
        event.target.reset();
        
        // Reload questions from server to get the actual data
        await loadQuestions();
    } catch (error) {
        showMessage('Failed to add question', 'error');
    }
}

async function handleEditQuestion(event) {
    event.preventDefault();
    
    const formData = new FormData(event.target);
    const id = parseInt(document.getElementById('editQuestionId').value);
    const title = formData.get('title').trim();
    
    if (!title) {
        showMessage('Please enter a question title', 'error');
        return;
    }
    
    try {
        const questionData = { title };
        await updateQuestion(id, questionData);
        
        showMessage('Question updated successfully!', 'success');
        cancelEdit();
        
        // Reload questions from server to get the updated data
        await loadQuestions();
    } catch (error) {
        showMessage('Failed to update question', 'error');
    }
}

async function loadQuestions() {
    try {
        questions = await getAllQuestions();
        renderQuestions();
        showMessage(`Loaded ${questions.length} question(s)`, 'info');
    } catch (error) {
        showMessage('Failed to load questions', 'error');
        console.error('Error loading questions:', error);
    }
}

async function editQuestion(id) {
    try {
        // Try to fetch the question details
        let question;
        try {
            question = await getQuestionById(id);
        } catch (error) {
            // If API call fails, use local data
            question = questions.find(q => q.id === id);
        }
        
        if (!question) {
            showMessage('Question not found', 'error');
            return;
        }
        
        // Populate edit form
        document.getElementById('editQuestionId').value = question.id;
        document.getElementById('editQuestionTitle').value = question.title;
        
        // Show edit section
        editSection.style.display = 'block';
        document.getElementById('editQuestionTitle').focus();
        
        // Scroll to edit section
        editSection.scrollIntoView({ behavior: 'smooth' });
        
        isEditing = true;
    } catch (error) {
        showMessage('Failed to load question details', 'error');
    }
}

function cancelEdit() {
    editSection.style.display = 'none';
    editQuestionForm.reset();
    isEditing = false;
}

async function confirmDeleteQuestion(id) {
    const question = questions.find(q => q.id === id);
    const questionTitle = question ? question.title : `Question ${id}`;
    
    if (confirm(`Are you sure you want to delete "${questionTitle}"?`)) {
        await deleteQuestionHandler(id);
    }
}

async function deleteQuestionHandler(id) {
    try {
        await deleteQuestion(id);
        
        showMessage('Question deleted successfully!', 'success');
        
        // If we're editing this question, cancel the edit
        if (isEditing && parseInt(document.getElementById('editQuestionId').value) === id) {
            cancelEdit();
        }
        
        // Reload questions from server to get the updated list
        await loadQuestions();
    } catch (error) {
        showMessage('Failed to delete question', 'error');
    }
}

async function viewQuestion(id) {
    try {
        let question;
        try {
            question = await getQuestionById(id);
            showMessage(`Loaded question: ${question.title}`, 'info');
        } catch (error) {
            // If API call fails, use local data
            question = questions.find(q => q.id === id);
            if (question) {
                showMessage(`Question: ${question.title} (from local data)`, 'info');
            } else {
                showMessage('Question not found', 'error');
            }
        }
    } catch (error) {
        showMessage('Failed to view question details', 'error');
    }
}

// Utility Functions
function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

// Keyboard shortcuts
document.addEventListener('keydown', function(event) {
    // Escape key to cancel edit
    if (event.key === 'Escape' && isEditing) {
        cancelEdit();
    }
    
    // Ctrl/Cmd + Enter to submit forms
    if ((event.ctrlKey || event.metaKey) && event.key === 'Enter') {
        const activeElement = document.activeElement;
        if (activeElement && activeElement.form) {
            activeElement.form.dispatchEvent(new Event('submit'));
        }
    }
});

// Handle form submission errors
window.addEventListener('unhandledrejection', function(event) {
    console.error('Unhandled promise rejection:', event.reason);
    showMessage('An unexpected error occurred', 'error');
});

// Export functions for global access (if needed)
window.editQuestion = editQuestion;
window.confirmDeleteQuestion = confirmDeleteQuestion;
window.viewQuestion = viewQuestion; 