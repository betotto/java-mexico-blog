
/*
 * No usar estos typos para el codigo, solo son para la documentación en vscode recuerda actualizarlos
 */

/**
 * Una accion de redux.
 * @typedef {object} Action
 * @property {string} type - El tipo de accion.
 * @property {object} [payload] - El payload de la accion.
 */
export const Action = {
  type: 'string'
};
/**
 * The initial state for cosaState.
 * @typedef {object} cosaState
 * @property {number} count - Un contador.
 */
export const initialState = {
  count: 0
};
