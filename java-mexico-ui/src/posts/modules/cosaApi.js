/**
 * Actualiza el counter en el cache.
 * @param {string} value El valor para guardar en cache.
 * @returns {Promise}
 */
export const saveCounter = value =>
  fetch(`/java-mexico-services-0.0.1/categorias/addCache/counter/${value}`, {
    method: 'POST'
  }).then(response => response.json());
/**
 * Obtiene el counter del cache.
 * @returns {Promise}
 */
export const getCounter = () =>
  fetch('/java-mexico-services-0.0.1/categorias/getCache/counter')
    .then(response => response.text());
